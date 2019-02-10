package com.dsource.idc.jellowboard;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.dsource.idc.jellowboard.makemyboard.MyBoards;
import com.dsource.idc.jellowboard.makemyboard.utility.IconDatabase;
import com.dsource.idc.jellowboard.utility.DefaultExceptionHandler;
import com.dsource.idc.jellowboard.utility.LanguageHelper;
import com.dsource.idc.jellowboard.utility.SessionManager;
import com.dsource.idc.jellowboard.verbiage_model.VerbiageDatabaseHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;

import ir.mahdi.mzip.zip.ZipArchive;

import static com.dsource.idc.jellowboard.utility.Analytics.bundleEvent;

public class SetupMMB extends AppCompatActivity {
    DownloadMan manager;
    RoundCornerProgressBar progressBar;
    private SessionManager mSession;
    String langCode = "en-rIN";
    private String mCheckConn;
    Boolean isConnected;
    TextView progressText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_download);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler(this));
        mSession = new SessionManager(this);
        if(mSession.isBoardDatabaseCreated())
        {
            startActivity(new Intent(this,MyBoards.class));
            finish();
        }
        else
            {


/* TODO Language Independent
        try {
            langCode =  getIntent().getExtras().getString(LCODE);
        }catch (Exception e)
        {
            e.printStackTrace();
        }*/
            progressBar = findViewById(R.id.pg);
            progressText = findViewById(R.id.progress_text);
            progressText.setText("Please wait while the icons are being downloaded. Do ensure there is an active internet connection at the time of download");
            progressBar.setMax(1);

            mCheckConn = getString(R.string.checkConnectivity);

            DownloadMan.ProgressReciever progressReciever = new DownloadMan.ProgressReciever() {
                @Override
                public void onprogress(int soFarBytes, int totalBytes) {
                    progressBar.setProgress((float) soFarBytes / totalBytes);
                }

                @Override
                public void onComplete() {
                    mSession.setDownloaded(langCode);
                    progressText.setText("Setting things up, Please wait...");
                    progressBar.setProgress(0);
                    progressBar.setMax(0);
                    createDatabase();
                }
            };


            if (langCode != null) {
                try {
                    isConnected = isConnected();
                    if (isConnected) {
                        manager = new DownloadMan(langCode, this, progressReciever);
                        manager.start();
                    } else {

                        Toast.makeText(this, mCheckConn, Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }

    private void createDatabase() {
        VerbiageDatabaseHelper databaseHelper = new VerbiageDatabaseHelper(this);
        databaseHelper.setOnProgressChangeListener(new VerbiageDatabaseHelper.ProgressListener() {
            @Override
            public void onProgressChanged(int progress, int max) {
                if(progressBar.getMax()==0)
                progressBar.setMax(max);
                Log.d("Max",max+"");
                progressBar.setProgress(progress);
            }

            @Override
            public void onComplete() {
                progressBar.setProgress(0);
                progressBar.setVisibility(View.GONE);
                progressText.setText("Finalizing setup, please wait...");
                createIconDatabase();
            }
        });
       new downloadVerbiage(this,databaseHelper).execute();

    }

    private void createIconDatabase() {
        IconDatabase iconDatabase = new IconDatabase(this,null);
        iconDatabase.setOnProgressChangeListener(new IconDatabase.ProgressListener() {
            @Override
            public void onProgressChanged(int progress, int max) {
/*
                Log.d("Max",max+"");
                progressBar.setMax(max);
                progressBar.setProgress(progress);*/
            }

            @Override
            public void onComplete() {
                startActivity(new Intent(SetupMMB.this, MyBoards.class));
                mSession.setBoardDatabaseStatus(1);
                finish();
            }
        });
        new jellowDatabase(this,iconDatabase).execute();
    }

    private boolean isConnected()
    {
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext((LanguageHelper.onAttachCustom(newBase,"")));
    }

}

 class DownloadMan {

    private FileDownloadListener fileDownloadListener;
    int id;
    private String localeCode;
    Context context;
    private ProgressReciever progressReciever;
    FirebaseAuth mAuth;

    public DownloadMan(String localeCode, Context context,ProgressReciever progressReciever) {
        this.localeCode = localeCode;
        this.context = context;
        this.progressReciever = progressReciever;
    }

    // any class using DownloadManager should implement this ProgressReciever for getting callbacks
    public interface ProgressReciever{
        void onprogress(int soFarBytes, int totalBytes);

        void onComplete();
    }

    public void start()
    {
        FirebaseStorage storage =  FirebaseStorage.getInstance(); // get an instance of storage


        StorageReference storageRef = storage.getReference(); // get a reference to a particular location


        StorageReference pathReference = storageRef.child("en-rIN_Icons"+".zip"); // select a particular file from that reference location

        pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                if(uri != null)
                    startDownload(uri);
                // Got the download URL for 'locale.zip'


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Toast.makeText(context,exception.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void startDownload(Uri url)
    {
        // get a reference to internal directory
        File en_dir = context.getDir(localeCode, Context.MODE_PRIVATE);

        // setup file downloader
        FileDownloader.setup(context);
        // add listener for callbacks
        fileDownloadListener = new FileDownloadListener() {
            @Override
            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            }

            @Override
            protected void started(BaseDownloadTask task) {
            }

            @Override
            protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
            }

            @Override
            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                progressReciever.onprogress(soFarBytes,totalBytes);

            }

            @Override
            protected void blockComplete(BaseDownloadTask task) {
            }

            @Override
            protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
            }

            @Override
            protected void completed(BaseDownloadTask task) {

                progressReciever.onprogress(1,1);
                extractZip();
                progressReciever.onComplete();
            }

            @Override
            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            }

            @Override
            protected void error(BaseDownloadTask task, Throwable e) {
                e.printStackTrace();
            }

            @Override
            protected void warn(BaseDownloadTask task) {

            }
        };


        // create a download task
        id = FileDownloader.getImpl().create(url.toString())
                .setPath(en_dir.getPath()+"/"+localeCode+".zip")
                .setForceReDownload(true)
                .setListener(fileDownloadListener).start();

    }

    private void extractZip() {
        File en_dir = context.getDir(localeCode, Context.MODE_PRIVATE);
        ZipArchive.unzip(en_dir.getPath()+"/"+localeCode+".zip",en_dir.getPath(),"");
        File zip = new File(en_dir.getPath(),localeCode+".zip");
        if(zip.exists()) zip.delete();

        //registerEvent();


    }

    // for Analytics Purpose
    private void registerEvent() {

        Bundle bundle = new Bundle();
        bundle.putString("Downloaded Language",localeCode);
        bundleEvent("Language",bundle);
    }


    public void pause()
    {
        FileDownloader.getImpl().pause(id);
    }

    public void resume()
    {
        if(id != 0)
        {
            // if file is not downloaded then start the download
            if(FileDownloader.getImpl().getSoFar(id) < FileDownloader.getImpl().getTotal(id))
            {
                start();
            }
        }
    }

}

class jellowDatabase extends AsyncTask
{

    Context context;
    IconDatabase database;
    public jellowDatabase(Context context,IconDatabase database) {
        this.context = context;
        this.database = database;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Object o) {
        super.onCancelled(o);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        database.createTable();
        return null;
    }
}
class downloadVerbiage extends AsyncTask
{

    Context context;
    VerbiageDatabaseHelper database;
    public downloadVerbiage(Context context,VerbiageDatabaseHelper database) {
        this.context = context;
        this.database = database;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Object o) {
        super.onCancelled(o);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        database.createTable();
        return null;
    }
}


