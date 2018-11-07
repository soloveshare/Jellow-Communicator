package com.dsource.idc.jellowintl.utility;

import android.content.Context;
import android.content.Intent;

import static com.dsource.idc.jellowintl.PathFactory.getAudioPath;

public class SpeechUtils {

    private static Boolean isNoTTSLang;

    public static void speak(Context context,String speechData){
        if(isNoTTSLang == null){
            isNoTTSLang = isNoTTSLanguage(context);
        }
        if(isNoTTSLang)
            playAudio(context,getAudioPath(context)+speechData);
        else
            speakSpeech(context,speechData);
    }

    public static boolean isNoTTSLanguage(Context context){
        SessionManager sessionManager = new SessionManager(context);
        return SessionManager.NoTTSLang.contains(sessionManager.getLanguage());
    }

    public static boolean isKeyboardAvailable(Context context){
        return isNoTTSLanguage(context);
    }



    private static void speakSpeech(Context context,String speechText) {
        Intent intent = new Intent("com.dsource.idc.jellowintl.SPEECH_TEXT");
        intent.putExtra("speechText", speechText.toLowerCase());
        context.sendBroadcast(intent);
    }

    private static void playAudio(Context context,String audioPath) {

        Intent intent = new Intent("com.dsource.idc.jellowintl.AUDIO_PATH");
        intent.putExtra("audioPath", audioPath);
        context.sendBroadcast(intent);
    }

    private static void playAudioInQueue(Context context,String audioPaths) {
        String filePath = getAudioPath(context);
        audioPaths = filePath + audioPaths.split(",")[0]+".mp3," +
                filePath + audioPaths.split(",")[1]+".mp3" ;
        Intent intent = new Intent("com.dsource.idc.jellowintl.AUDIO_IN_QUEUE");
        intent.putExtra("speechTextInQueue", audioPaths);
        context.sendBroadcast(intent);
    }

}
