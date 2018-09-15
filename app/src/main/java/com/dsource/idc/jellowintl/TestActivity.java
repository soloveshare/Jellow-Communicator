package com.dsource.idc.jellowintl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.dsource.idc.jellowintl.utility.SessionManager;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        /**
         * TODO: 1) Check the multi language support with new zip files - Finished
         * TODO: 2) Fiddle with the Search Activity Icon Database - Finished
         * TODO: 3) Test entire app
         * TODO: 4) Some Array in the Sequence activity Still depend on Resource Arrays - No Alternative
         * TODO: 5) Tell to fix hindi text in en_rIN JSON file
         */

        //String fc = readFileFromRawDirectory(this, R.raw.a);


        String[] fnames = {"0107140002GG.png", "0104040000GG.png", "0103010006GG.png", "0101EE.png", "0107020008GG.png", "0106050000GG.png", "0105050007GG.png", "0108070009GG.png", "0102100003GG.png", "0108040005GG.png", "0102040028GG.png", "0102040038GG.png", "0104020012GG.png", "0102030009SS.png", "0105010036GG.png", "0105040004GG.png", "0105030010GG.png", "0103040007GG.png", "0107020017GG.png", "0102020005SS.png", "0101030016GG.png", "0105100002GG.png", "0103040003GG.png", "0107010007GG.png", "0104030007GG.png", "0105030008GG.png", "0103030001GG.png", "0107020014GG.png", "0107130013GG.png", "0101020007GG.png", "0104050000GG.png", "0104040002GG.png", "0101030017GG.png", "0105090000GG.png", "0109070000GG.png", "0101020012GG.png", "0103020014GG.png", "0107090001GG.png", "0101020000GG.png", "0105060005GG.png", "0103050016GG.png", "0103060000GG.png", "0103050013GG.png", "0107130003GG.png", "0103050014GG.png", "0104060007GG.png", "0102040021GG.png", "0102050000GG.png", "0107050003GG.png", "0101020004GG.png", "0102010008SS.png", "0105100006GG.png", "0107080004GG.png", "0102010000SS.png", "0101010011GG.png", "0106180000GG.png", "0107020000GG.png", "0105010001GG.png", "0105010029GG.png", "0105100005GG.png", "0103020011GG.png", "0102030005SS.png", "0103070008GG.png", "0105010011GG.png", "0104010014GG.png", "0103020002GG.png", "0105010040GG.png", "0101010008GG.png", "0108040007GG.png", "0107130004GG.png", "0104010004GG.png", "0101040008GG.png", "0103020001GG.png", "0107150000GG.png", "0102EE.png", "0107040002GG.png", "0104020010GG.png", "0105010008GG.png", "0102100010GG.png", "0102070019GG.png", "0107060002GG.png", "0108060003GG.png", "0102100015GG.png", "0102090000SS.png", "0105060001GG.png", "0104050002GG.png", "0102070015GG.png", "0108030006GG.png", "0107110004GG.png", "0103050008GG.png", "0107020011GG.png", "0102030000SS.png", "0108050001GG.png", "0104010001GG.png", "0107020007GG.png", "0105070007GG.png", "0103080009GG.png", "0107070005GG.png", "0102050006GG.png", "0103050001GG.png", "0103060004GG.png", "0105020013GG.png", "0102070001GG.png", "0102030002SS.png", "0102040008GG.png", "0104020016GG.png", "0102100006GG.png", "0103020013GG.png", "0102040016GG.png", "0104010000GG.png", "0107130012GG.png", "0103070004GG.png", "0104020015GG.png", "0102010003SS.png", "0102070007GG.png", "0102090003SS.png", "0105010026GG.png", "0102030017SS.png", "0102040030GG.png", "0109020000GG.png", "0104010002GG.png", "0108020004GG.png", "0103010004GG.png", "0103050017GG.png", "0103080008GG.png", "0107030005GG.png", "0105080026GG.png", "0105020007GG.png", "0102050005GG.png", "0109010000GG.png", "0105050011GG.png", "0102100021GG.png", "0102030014SS.png", "0104020003GG.png", "0101030011GG.png", "0106120000GG.png", "0102030011SS.png", "0102100004GG.png", "0105030000GG.png", "0105030009GG.png", "0106000000GG.png", "0102050001GG.png", "0107090003GG.png", "0101030019GG.png", "0105080025GG.png", "0107160002GG.png", "0105020008GG.png", "0108070001GG.png", "0102040033GG.png", "0103080006GG.png", "0102020003SS.png", "0104030004GG.png", "0102040029GG.png", "0101030007GG.png", "0102070016GG.png", "0103020022GG.png", "0103070002GG.png", "0101020014GG.png", "0105010045GG.png", "0103060008GG.png", "0105080001GG.png", "0108010004GG.png", "0107010005GG.png", "0104010013GG.png", "0101030012GG.png", "0102070013GG.png", "0107090006GG.png", "0107110003GG.png", "0108010005GG.png", "0102030008SS.png", "0102010005SS.png", "0105010003GG.png", "0105080021GG.png", "0102060003GG.png", "0104010012GG.png", "0105080014GG.png", "0108010003GG.png", "0105070014GG.png", "0105010013GG.png", "0104030008GG.png", "0107020003GG.png", "0108030016GG.png", "0107020001GG.png", "0102040009GG.png", "0102080009SS.png", "0105EE.png", "0105050002GG.png", "0107010002GG.png", "0109220000GG.png", "0107020002GG.png", "0103060022GG.png", "0101030006GG.png", "0104010005GG.png", "0105070000GG.png", "0104010009GG.png", "0101040001GG.png", "0105020001GG.png", "0102020006SS.png", "0104050001GG.png", "0102070006GG.png", "0105080007GG.png", "0105060000GG.png", "0101020001GG.png", "0105010023GG.png", "0105010025GG.png", "0102070017GG.png", "0107080002GG.png", "0107050001GG.png", "0105010038GG.png", "0105010039GG.png", "0105020003GG.png", "0105080002GG.png", "0105090004GG.png", "0105060013GG.png", "0102040006GG.png", "0108060000GG.png", "0108010006GG.png", "0102100023GG.png", "0107050002GG.png", "0102060009GG.png", "0102040032GG.png", "0105030012GG.png", "0102010002SS.png", "0105080010GG.png", "0106090000GG.png", "0102090004SS.png", "0103030010GG.png", "0105090002GG.png", "0104060008GG.png", "0106100000GG.png", "0103020017GG.png", "0105060008GG.png", "0102090007SS.png", "0103010013GG.png", "0102090006SS.png", "0102040036GG.png", "0101010010GG.png", "0103000000GG.png", "0102010001SS.png", "0104050004GG.png", "0104060004GG.png", "0103060025GG.png", "0104030005GG.png", "0105020017GG.png", "0105040009GG.png", "0102050007GG.png", "0102040014GG.png", "0107080008GG.png", "0102030013SS.png", "0107090004GG.png", "0102060002GG.png", "0102100008GG.png", "0105020015GG.png", "0107010004GG.png", "0101030015GG.png", "0107020015GG.png", "0107130010GG.png", "0102070010GG.png", "0102080003SS.png", "0102040025GG.png", "0105010035GG.png", "0102040018GG.png", "0101010001GG.png", "0109210000GG.png", "0102020000SS.png", "0109040000GG.png", "0103050006GG.png", "0106170000GG.png", "0105030013GG.png", "0102040022GG.png", "0107130009GG.png", "0108060006GG.png", "0102100012GG.png", "0105010010GG.png", "0108030005GG.png", "0102100007GG.png", "0107090000GG.png", "0108020005GG.png", "0106EE.png", "0102030010SS.png", "0109180000GG.png", "0105060003GG.png", "0107160000GG.png", "0101040004GG.png", "0107140009GG.png", "0102020002SS.png", "0105050017GG.png", "0109000000GG.png", "0101020002GG.png", "0102080002SS.png", "0108030002GG.png", "0107130015GG.png", "0105070008GG.png", "0105060012GG.png", "0107130006GG.png", "0105010012GG.png", "0102040020GG.png", "0105010043GG.png", "0109130000GG.png", "0107010000GG.png", "0101000000GG.png", "0102040034GG.png", "0106030000GG.png", "0108020008GG.png", "0103070003GG.png", "0102060005GG.png", "0105070012GG.png", "0108040003GG.png", "0101020008GG.png", "0108060012GG.png", "0105080005GG.png", "0105060004GG.png", "0105070002GG.png", "0105100008GG.png", "0104020004GG.png", "0105050016GG.png", "0105020005GG.png", "0108020006GG.png", "0103050015GG.png", "0102010007SS.png", "0103030004GG.png", "0105080023GG.png", "0105080008GG.png", "0103020018GG.png", "0103080001GG.png", "0105030002GG.png", "0105030017GG.png", "0105060014GG.png", "0107130007GG.png", "0102040005GG.png", "0105050003GG.png", "0107050004GG.png", "0107020006GG.png", "0105050013GG.png", "0105010033GG.png", "0108070012GG.png", "0107100003GG.png", "0101010012GG.png", "0107070003GG.png", "0107020009GG.png", "0105090005GG.png", "0101020006GG.png", "0105040006GG.png", "0106060000GG.png", "0105060006GG.png", "0103060019GG.png", "0107130008GG.png", "0105050012GG.png", "0105060009GG.png", "0108060008GG.png", "0102080010SS.png", "0107160001GG.png", "0101040000GG.png", "0102070005GG.png", "0103070005GG.png", "0109090000GG.png", "0104030000GG.png", "0104020013GG.png", "0105080012GG.png", "0102080007SS.png", "0106070000GG.png", "0107120004GG.png", "0105040010GG.png", "0105080009GG.png", "0105090006GG.png", "0105070009GG.png", "0101040002GG.png", "0105010009GG.png", "0102040004GG.png", "0102030001SS.png", "0103040000GG.png", "0103010016GG.png", "0103010011GG.png", "0105040011GG.png", "0108010007GG.png", "0108030007GG.png", "0102040013GG.png", "0101030005GG.png", "0104EE.png", "0102030006SS.png", "0102040023GG.png", "0103010001GG.png", "0102100019GG.png", "0102060000GG.png", "0104060000GG.png", "0105070011GG.png", "0105040008GG.png", "0107130001GG.png", "0108030013GG.png", "0104030006GG.png", "0108030004GG.png", "0105010021GG.png", "0105020012GG.png", "0103020020GG.png", "0107030000GG.png", "0105040012GG.png", "0101020009GG.png", "0105030018GG.png", "0105040007GG.png", "0107070002GG.png", "0105010015GG.png", "0103050007GG.png", "0108030012GG.png", "0109030000GG.png", "0107140007GG.png", "0104060001GG.png", "0103060014GG.png", "0104020001GG.png", "0105080013GG.png", "0105070015GG.png", "0103050012GG.png", "0105070003GG.png", "0107140004GG.png", "0103010003GG.png", "0107060000GG.png", "0103050009GG.png", "0103060011GG.png", "0105010019GG.png", "0107080001GG.png", "0107100005GG.png", "0105050008GG.png", "0103050004GG.png", "0107110000GG.png", "0101020017GG.png", "0104010006GG.png", "0108020000GG.png", "0102070018GG.png", "0102050002GG.png", "0102100022GG.png", "0107100004GG.png", "0104020005GG.png", "0108060011GG.png", "0102030016SS.png", "0101030010GG.png", "0103030005GG.png", "0103060016GG.png", "0102100002GG.png", "0101030018GG.png", "0108030009GG.png", "0107090005GG.png", "0105070005GG.png", "0104040005GG.png", "0102040001GG.png", "0103010005GG.png", "0103020016GG.png", "0101010007GG.png", "0105080015GG.png", "0104010010GG.png", "0105080022GG.png", "0108040004GG.png", "0105010000GG.png", "0106040000GG.png", "0107010003GG.png", "0107140008GG.png", "0103020010GG.png", "0105010030GG.png", "0108040000GG.png", "0103010007GG.png", "0105030006GG.png", "0103020021GG.png", "0105060007GG.png", "0109050000GG.png", "0105020018GG.png", "0101010013GG.png", "0104010007GG.png", "0104020000GG.png", "0103010009GG.png", "0103060015GG.png", "0103050003GG.png", "0108060010GG.png", "0105010024GG.png", "0103030006GG.png", "0107120002GG.png", "0105080016GG.png", "0104020002GG.png", "0105080019GG.png", "0102020004SS.png", "0105020002GG.png", "0108070008GG.png", "0107030001GG.png", "0108030014GG.png", "0102080006SS.png", "0103030009GG.png", "0101010005GG.png", "0105060015GG.png", "0105090003GG.png", "0107130011GG.png", "0103060009GG.png", "0107130014GG.png", "0103070006GG.png", "0109230000GG.png", "0102100017GG.png", "0102040003GG.png", "0102030012SS.png", "0101020013GG.png", "0103080003GG.png", "0105010006GG.png", "0102100009GG.png", "0103010017GG.png", "0104030011GG.png", "0105030015GG.png", "0108000000GG.png", "0104020017GG.png", "0107080007GG.png", "0107120001GG.png", "0105030014GG.png", "0107140001GG.png", "0103020000GG.png", "0102100005GG.png", "0107060001GG.png", "0105050009GG.png", "0103020004GG.png", "0102010006SS.png", "0102070012GG.png", "0105020020GG.png", "0105090001GG.png", "0108010002GG.png", "0105000000GG.png", "0103020008GG.png", "0107090002GG.png", "0105020004GG.png", "0109080000GG.png", "0101030001GG.png", "0107080006GG.png", "0108070004GG.png", "0102040026GG.png", "0103040004GG.png", "0102080005SS.png", "0103060002GG.png", "0102020007SS.png", "0106150000GG.png", "0109100000GG.png", "0104020008GG.png", "0108040006GG.png", "0102100013GG.png", "0101020016GG.png", "0103060018GG.png", "0104060002GG.png", "0104020018GG.png", "0103060021GG.png", "0101020010GG.png", "0104030009GG.png", "0105050004GG.png", "0102080008SS.png", "0105040000GG.png", "0107020004GG.png", "0104030010GG.png", "0105040001GG.png", "0107100000GG.png", "0103050011GG.png", "0104030001GG.png", "0105050014GG.png", "0108040001GG.png", "0102040031GG.png", "0107020013GG.png", "0108030000GG.png", "0105010005GG.png", "0105080024GG.png", "0108070000GG.png", "0102040015GG.png", "0108010000GG.png", "0104000000GG.png", "0108050005GG.png", "0107120000GG.png", "0107020010GG.png", "0108030008GG.png", "0105070006GG.png", "0102040027GG.png", "0109170000GG.png", "0101020005GG.png", "0102040010GG.png", "0105010041GG.png", "0105010037GG.png", "0102040011GG.png", "0108030003GG.png", "0108070010GG.png", "0101010014GG.png", "0108050004GG.png", "0108020007GG.png", "0103050005GG.png", "0103080000GG.png", "0106140000GG.png", "0106110000GG.png", "0107130005GG.png", "0104040001GG.png", "0102090005SS.png", "0101030004GG.png", "0105060010GG.png", "0105050006GG.png", "0102070000GG.png", "0105030005GG.png", "0104030003GG.png", "0102100020GG.png", "0107060003GG.png", "0102020009SS.png", "0103020019GG.png", "0103020005GG.png", "0104060005GG.png", "0103020009GG.png", "0109110000GG.png", "0102070004GG.png", "0107010006GG.png", "0103010002GG.png", "0102040024GG.png", "0102080004SS.png", "0102080013SS.png", "0103080007GG.png", "0107150003GG.png", "0105010022GG.png", "0107040000GG.png", "0105040005GG.png", "0103060013GG.png", "0107110001GG.png", "0105030003GG.png", "0105080006GG.png", "0105030004GG.png", "0103020003GG.png", "0106160000GG.png", "0109150000GG.png", "0105020006GG.png", "0103010014GG.png", "0107130002GG.png", "0103030008GG.png", "0105090009GG.png", "0105100007GG.png", "0108070006GG.png", "0105010044GG.png", "0102080012SS.png", "0104020009GG.png", "0102030004SS.png", "0103060024GG.png", "0103010008GG.png", "0102100016GG.png", "0108040002GG.png", "0107140006GG.png", "0102040000GG.png", "0104020006GG.png", "0108030015GG.png", "0101030013GG.png", "0102030003SS.png", "0108070002GG.png", "0105080017GG.png", "0108060004GG.png", "0105070010GG.png", "0108030001GG.png", "0105070016GG.png", "0105010002GG.png", "0102030007SS.png", "0102040017GG.png", "0105060011GG.png", "0102070003GG.png", "0105010017GG.png", "0105080004GG.png", "0107080000GG.png", "0107010001GG.png", "0108020002GG.png", "0105040013GG.png", "0104060003GG.png", "0106130000GG.png", "0108020003GG.png", "0107150001GG.png", "0105070001GG.png", "0109120000GG.png", "0103050002GG.png", "0101040003GG.png", "0105010004GG.png", "0104060006GG.png", "0108060002GG.png", "0102060008GG.png", "0103060010GG.png", "0105040003GG.png", "0105080011GG.png", "0108070005GG.png", "0103010010GG.png", "0105090008GG.png", "0105080018GG.png", "0108070007GG.png", "0101030003GG.png", "0108050003GG.png", "0108060009GG.png", "0102020008SS.png", "0107070004GG.png", "0105020000GG.png", "0105080000GG.png", "0105100004GG.png", "0105100000GG.png", "0104010011GG.png", "0103020015GG.png", "0108030010GG.png", "0104040003GG.png", "0102040012GG.png", "0107150004GG.png", "0102060006GG.png", "0103050000GG.png", "0105030019GG.png", "0101010006GG.png", "0102050004GG.png", "0108050000GG.png", "0108060007GG.png", "0103010015GG.png", "0108030011GG.png", "0107140003GG.png", "0102060001GG.png", "0103030003GG.png", "0105010042GG.png", "0101010000GG.png", "0103030007GG.png", "0105090007GG.png", "0105020019GG.png", "0107030004GG.png", "0103050010GG.png", "0105020011GG.png", "0102060007GG.png", "0103060017GG.png", "0103MS.png", "0104040004GG.png", "0103060012GG.png", "0103060005GG.png", "0108020001GG.png", "0102040037GG.png", "0102050003GG.png", "0101030014GG.png", "0105080020GG.png", "0104040006GG.png", "0103060001GG.png", "0102010004SS.png", "0103010012GG.png", "0102070011GG.png", "0102100014GG.png", "0102100018GG.png", "0103080004GG.png", "0109140000GG.png", "0103020007GG.png", "0102100001GG.png", "0102020001SS.png", "0102080001SS.png", "0101010009GG.png", "0107040001GG.png", "0103070001GG.png", "0105010032GG.png", "0107140000GG.png", "0107100001GG.png", "0106080000GG.png", "0107000000GG.png", "0103060003GG.png", "0102040002GG.png", "0102100011GG.png", "0105020010GG.png", "0105060016GG.png", "0103060006GG.png", "0105050010GG.png", "0102100000GG.png", "0101030002GG.png", "0107070000GG.png", "0104050003GG.png", "0108050006GG.png", "0103070000GG.png", "0105100003GG.png", "0109060000GG.png", "0105020009GG.png", "0108070011GG.png", "0101010002GG.png", "0102070002GG.png", "0102040007GG.png", "0105010034GG.png", "0101020003GG.png", "0103040001GG.png", "0105010016GG.png", "0101040005GG.png", "0102030015SS.png", "0103080002GG.png", "0101010004GG.png", "0103040008GG.png", "0107020016GG.png", "0108070003GG.png", "0105010018GG.png", "0104030002GG.png", "0103020012GG.png", "0105020016GG.png", "0105030016GG.png", "0102080011SS.png", "0107120003GG.png", "0105010007GG.png", "0107030003GG.png", "0101030009GG.png", "0104020007GG.png", "0103060007GG.png", "0101020015GG.png", "0108060001GG.png", "0105050001GG.png", "0107080003GG.png", "0108060005GG.png", "0102000000GG.png", "0107030002GG.png", "0105030007GG.png", "0102040019GG.png", "0107050000GG.png", "0107090007GG.png", "0107020005GG.png", "0105010020GG.png", "0105010027GG.png", "0107020012GG.png", "0101MS.png", "0107150005GG.png", "0105020014GG.png", "0103060023GG.png", "0105070004GG.png", "0103010000GG.png", "0105030001GG.png", "0101030008GG.png", "0105040002GG.png", "0107100002GG.png", "0103060020GG.png", "0107070001GG.png", "0105030011GG.png", "0108010008GG.png", "0108050002GG.png", "0101040009GG.png", "0109190000GG.png", "0103030000GG.png", "0107130000GG.png", "0105050015GG.png", "0102070009GG.png", "0103040006GG.png", "0107120005GG.png", "0102070008GG.png", "0101040007GG.png", "0105050000GG.png", "0107060004GG.png", "0102080000SS.png", "0105010031GG.png", "0102MS.png", "0103040005GG.png", "0105080003GG.png", "0105050005GG.png", "0101020011GG.png", "0103030002GG.png", "0102060004GG.png", "0105070013GG.png", "0107140005GG.png", "0104010015GG.png", "0109160000GG.png", "0102040035GG.png", "0107110002GG.png", "0102090001SS.png", "0105010028GG.png", "0105010046GG.png", "0109200000GG.png", "0103020006GG.png", "0105060002GG.png", "0104010003GG.png", "0105100001GG.png", "0103EE.png", "0105010014GG.png", "0102070014GG.png", "0101040006GG.png", "0101030000GG.png", "0104020011GG.png", "0107150002GG.png", "0102090002SS.png", "0106010000GG.png", "0107080005GG.png", "0106020000GG.png", "0108010001GG.png", "0103070007GG.png", "0103080005GG.png", "0104010008GG.png", "0103040002GG.png", "0104020014GG.png", "0101010003GG.png"};

        SessionManager sessionManager = new SessionManager(this);
        sessionManager.setLanguage(SessionManager.ENG_IN);

        File en_dir = this.getDir(SessionManager.ENG_IN, Context.MODE_PRIVATE);


        for (int i = 0; i < fnames.length; i++) {

            File file = new File(en_dir, fnames[i]);

            try {
                copyImage(this,R.raw.a,file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            /*try {
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }


       /*String b[] = en_dir.list();
        for(String  h : b){

            // L3 : "[0-9]{6}([1-9][0-9]{3}|[0-9][1-9][0-9]{2}|[0-9]{2}[1-9][0-9]|[0-9]{3}[1-9])GG"
            //  or "\d{6}([1-9]\d{3}|\d{3}[1-9]|\d{2}[1-9]\d|\d[1-9]\d{2})GG"
            //  Java version : "\\d{6}([1-9]\\d{3}|\\d{3}[1-9]|\\d{2}[1-9]\\d|\\d[1-9]\\d{2})GG"
            // L2 : "[0-9]{4}([1-9][1-9]|[0-9][1-9]|[1-9][0-9])0{4}GG" or "[0-9]{4}([1-9][1-9]|[0][1-9]|[1-9][0])0{4}GG"
            // L1 : "[0-9]{4}0{6}GG"
            if(h.matches("\\d{6}([1-9]\\d{3}|\\d{3}[1-9]|\\d{2}[1-9]\\d|\\d[1-9]\\d{2})GG")){

                System.out.println("regex "+" matched :"+h);

            } else {
                System.out.println("regex "+" failed :"+h);
            }
        }*/

        String[] n = IconFactory.removeFileExtension(IconFactory.getL2Icons(en_dir, "01","03"));

        for (String a : n) {
            System.out.println("regex " + " matched :" + a);
        }


        String filename = "map.json";
        File json = new File(en_dir.getAbsolutePath() + "/" + filename);
        String fileContents = readFileFromRawDirectory(this, R.raw.en);
        FileOutputStream outputStream;

        try {
            outputStream = new FileOutputStream(json);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Icon[] a = TextFactory.getIconObjects(json, n);

        for (Icon b : a) {
            System.out.println("regex " + " matched :" + b.getDisplay_Label());
        }

        /*MiscellaneousIcon[] a = TextFactory.getMiscellaneousIconObjects(json, n);

        for (MiscellaneousIcon b : a) {
            System.out.println("regex " + " matched :" + b.getTitle());
        }*/


    }

    private String readFileFromRawDirectory(Context context, int resourceId) {
        InputStream inputStream = context.getResources().openRawResource(resourceId);
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(buffer);
            byteArrayOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString();
    }

    private void copyImage(Context context, int resourceId,File targetFile){


        try {
            InputStream inputStream = context.getResources().openRawResource(resourceId);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
            inputStream.close();
            outStream.close();

        } catch (Exception e){

        }

    }

    private String readFromFile(Context context, @NonNull String filename) {
        File file = new File(context.getFilesDir(), filename);
        StringBuilder text = new StringBuilder();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line);
            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return text.toString();

    }
}
