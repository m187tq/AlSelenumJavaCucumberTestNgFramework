package com.automationteststore.maps;

import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;

import java.util.HashMap;
import java.util.Map;

public class PageObjectUrlMap {

    private static Map<String, String> pageUrlMap;

    static {
        pageUrlMap = new HashMap<>();
        pageUrlMap.put("Login", GlobalVarsHelper.getInstance().getCandidateURL() + "/candidate/auth/login");
        pageUrlMap.put("Applications",
                GlobalVarsHelper.getInstance().getCandidateURL() + "/candidate/application/list");
        pageUrlMap.put("Add academic qualification", GlobalVarsHelper.getInstance().getCandidateURL()
                + "/candidate/application/JOB-REF/academic-qualifications/input/");
        pageUrlMap.put("Academic qualifications", GlobalVarsHelper.getInstance().getCandidateURL()
                + "/candidate/application/JOB-REF/academic-qualifications/table");
        pageUrlMap.put("Person Specification",
                GlobalVarsHelper.getInstance().getURL() + "/vacancy/VACANCY-ID/person-specification/");
        pageUrlMap.put("Professional and vocational training table", GlobalVarsHelper.getInstance().getCandidateURL()
                + "/candidate/application/JOB-REF/professional-training/table");
        pageUrlMap.put("Professional and vocational training", GlobalVarsHelper.getInstance().getCandidateURL()
                + "/candidate/application/JOB-REF/professional-training/input/");
        pageUrlMap.put("Job details",
                GlobalVarsHelper.getInstance().getCandidateURL() + "/candidate/application/JOB-REF/job-history/input/");
        pageUrlMap.put("Job history table",
                GlobalVarsHelper.getInstance().getCandidateURL() + "/candidate/application/JOB-REF/job-history/table");
    }

    public static String getUrlForPage(String pageName) {
        String pageUrl = pageUrlMap.get(pageName);
        return pageUrl;
    }

    public static String getUrlThatIncludesJobRefForPage(String pageName, String jobReference) {
        String pageUrl = pageUrlMap.get(pageName);
        pageUrl = pageUrl.replace("JOB-REF", jobReference);
        return pageUrl;
    }

    public static String getUrlThatIncludesVacancyIdForPage(String pageName, String vacancyId) {
        String pageUrl = pageUrlMap.get(pageName);
        pageUrl = pageUrl.replace("VACANCY-ID", vacancyId);
        return pageUrl;
    }

}
