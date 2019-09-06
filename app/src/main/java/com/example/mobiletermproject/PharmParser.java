package com.example.mobiletermproject;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class PharmParser {
    public final static String PHARM_URL = "http://openapi.hira.or.kr/openapi/service/pharmacyInfoService/getParmacyBasisList";
    public final static String KEY
            = "hk%2B%2F3qSyS7tQc08tax5dVJRQDWjKpE4YgcHBXfamdWgYv63vQEu%2BWZ%2BvdhLwY16wtCBH%2FBsqX0nLzp38R5T4xg%3D%3D";

    public PharmParser() {
        try {
            apiParserSearch();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     *
     * @throws Exception
     */
    public ArrayList<PharmDTO> apiParserSearch() throws Exception {
        URL url = new URL(getURLParam(null));

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        xpp.setInput(bis, "utf-8");

        String tag = null;
        int event_type = xpp.getEventType();

        ArrayList<PharmDTO> list = new ArrayList<PharmDTO>();

        String xpos = null, ypos= null,name=null;
        while (event_type != XmlPullParser.END_DOCUMENT) {
            if (event_type == XmlPullParser.START_TAG) {
                tag = xpp.getName();
            } else if (event_type == XmlPullParser.TEXT) {
                /**
                 * 약국의 주소만 가져와본다.
                 */
                if(tag.equals("XPos")){
                    xpos = xpp.getText();
                    System.out.println(xpos);
                }else if(tag.equals("YPos")){
                    ypos = xpp.getText();
                }else if(tag.equals("yadmNm")){
                    name = xpp.getText();
                }
            } else if (event_type == XmlPullParser.END_TAG) {
                tag = xpp.getName();
                if (tag.equals("item")) {
                    PharmDTO entity = new PharmDTO();
                    entity.setXpos(Double.valueOf(xpos));
                    entity.setYpos(Double.valueOf(ypos));
                    entity.setName(name);

                    list.add(entity);
                }
            }
            event_type = xpp.next();
        }
        System.out.println(list.size());

        return list;
    }




    private String getURLParam(String search){
        String url = PHARM_URL+"?ServiceKey="+KEY;
        if(search != null){
            url = url+"&yadmNm"+search;
        }
        return url;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new PharmParser();
    }
}
