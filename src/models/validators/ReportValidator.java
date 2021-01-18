package models.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Report;

public class ReportValidator {
    public static List<String> validate(Report r) {
        List<String> errors = new ArrayList<String>();

        String title_error = _validateTitle(r.getTitle());
        if(!title_error.equals("")) {
            errors.add(title_error);
        }

        String content_error = _validateContent(r.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        String time_error = _validateTime(r.getStart_time(),r.getEnd_time() );
        if(!time_error.equals("")) {
            errors.add(time_error);
        }

        String start_time_error = _validateStart_time(r.getStart_time());
        if(!start_time_error.equals("")) {
            errors.add(start_time_error);
        }

        String end_time_error = _validateEnd_time(r.getEnd_time());
        if(!end_time_error.equals("")) {
            errors.add(end_time_error);
        }

        return errors;
    }

    private static String _validateTitle(String title) {
        if(title == null || title.equals("")) {
            return "タイトルを入力してください。";
            }

        return "";
    }

    private static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "内容を入力してください。";
            }

        return "";
    }

        //出勤時間と退勤時間の順序が正しいかチェックするメソッド
    private static String _validateTime(String start_time ,String end_time){
        //出勤時間と退勤時間が入力されていたら
        if(_validateStart_time(start_time).equals("") && _validateEnd_time(end_time).equals("")){

            String[] vst = start_time.split(":", 0);
            String[] vet = end_time.split(":", 0);


            if(Integer.parseInt(vst[0]) -Integer.parseInt(vet[0]) > 0){
                return "時間を確認してください。";
            }else if(Integer.parseInt(vst[0]) -Integer.parseInt(vet[0]) == 0  &&  Integer.parseInt(vst[1]) -Integer.parseInt(vet[1]) >= 0){
                return "時間を確認してください。";
            }
        }

        return "";
    }

    private static String _validateStart_time(String start_time) {
        Pattern p_st = Pattern.compile("^([0-1][0-9]|[2][0-3]):[0-5][0-9]$");
        Matcher m_st = p_st.matcher(rightstring("0"+start_time, 5));
        if ( !m_st.find() )
            return "出勤時間を入力してください。";

        if(start_time == null || start_time.equals("")){
            return "出勤時間を入力してください。";
        }

        return "";
    }

    private static String _validateEnd_time(String end_time) {
        Pattern p_et = Pattern.compile("^([0-1][0-9]|[2][0-3]):[0-5][0-9]$");
        Matcher m_et = p_et.matcher(rightstring_et("0"+end_time, 5));
        if ( !m_et.find() )
            return "退勤時間を入力してください。";

        if(end_time == null || end_time.equals("")) {
            return "退勤時間を入力してください。";
            }

        return "";
    }

    public static String rightstring(String start_time, int length) {
        try {
          if ( start_time.length() >= length )
            return start_time.substring(start_time.length() - length);
          else
            return start_time.substring(1);
        } catch ( Exception e ) {
          return start_time;
        }
      }

    public static String rightstring_et(String end_time, int length) {
        try {
          if ( end_time.length() >= length )
            return end_time.substring(end_time.length() - length);
          else
            return end_time.substring(1);
        } catch ( Exception e ) {
          return end_time;
        }
      }


}