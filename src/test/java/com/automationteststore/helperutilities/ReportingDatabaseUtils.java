package com.automationteststore.helperutilities;

import com.automationteststore.helperutilities.date.DataSettings;
import com.automationteststore.testData.admin.AdminTestData;
import lombok.Getter;
import lombok.Setter;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.automationteststore.helperutilities.VacancyPublishWithAPICall.loadDatabaseDetails;

public class ReportingDatabaseUtils {
    public static DataSettings data;
    @Getter
    @Setter
    private static int total;

    public static Connection reportingDatabaseConnection() throws IOException {
        data = loadDatabaseDetails();
        Connect conn = null;
        try {
            conn = new Connect();
            conn.setConnection(data.getReportingDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
        } catch (Exception e) {
            System.out.println("Cannot able to connect to the reporting database...." + e.getMessage());
        }
        assert conn != null;
        return conn.getConnection();
    }

    public static void generateMasterFlexReport(String employerCode, String vacancyDateFrom, String vacancyDateTo)
            throws Exception {
        String query = "SELECT * FROM mv_applicationexport_report WHERE \"Employer code\" = '" + employerCode
                + "' AND \"Date vacancy created\" BETWEEN '" + vacancyDateFrom + "' AND '" + vacancyDateTo
                + "'AND report_as_open IS NOT NULL AND report_as_closed IS NOT NULL ";
        CopyManager copyManager = new CopyManager((BaseConnection) reportingDatabaseConnection());
        File file = new File(AdminTestData.REPORTING_DOWNLOAD_FILEPATH + "/masterFlex-output.csv");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        copyManager.copyOut("COPY (" + query + ") TO STDOUT WITH CSV HEADER", fileOutputStream);
    }

    public static Map<String, String> generateVacancyNumbersReport(String employerCode, String vacancyDateFrom,
                                                                   String vacancyDateTo, String[] staffGroup) throws IOException, SQLException {
        Map<String, String> staffGroupMap = new HashMap<>();
        for (String group : staffGroup) {
            String query = "SELECT COUNT(staff_group) AS staffCount FROM \"reporting-data_01\".mv_common_vacancy_vacancy_information WHERE employer_code = '"
                    + employerCode + "' AND " + "created_date BETWEEN '" + vacancyDateFrom + "' AND '" + vacancyDateTo
                    + "' AND report_as_open IS NOT NULL AND report_as_closed IS NOT NULL AND staff_group ='" + group
                    + "'";
            Connection conn = reportingDatabaseConnection();
            Statement statement = conn.createStatement();
            ResultSet set = statement.executeQuery(query);
            set.next();
            if (group.equalsIgnoreCase("ALLIED_HEALTH_PROF")) {
                staffGroupMap.put("Allied Health Professionals", set.getString("staffCount"));
            } else if (group.equalsIgnoreCase("CLINICAL_SERVICES")) {
                staffGroupMap.put("Additional Clinical Services", set.getString("staffCount"));

            } else if (group.equalsIgnoreCase("PROF_SCIENTIFIC_AND_TECHNICAL")) {
                staffGroupMap.put("Additional Professional Scientific & Technical", set.getString("staffCount"));

            } else if (group.equalsIgnoreCase("ADMINISTRATIVE_AND_CLERICAL")) {
                staffGroupMap.put("Administrative & Clerical", set.getString("staffCount"));

            } else if (group.equalsIgnoreCase("ESTATES_AND_ACILLARY")) {
                staffGroupMap.put("Estates & Ancillary", set.getString("staffCount"));

            } else if (group.equalsIgnoreCase("HEALTHCARE_SCIENTISTS")) {
                staffGroupMap.put("Healthcare Scientists", set.getString("staffCount"));

            } else if (group.equalsIgnoreCase("MEDICAL_AND_DENTAL")) {
                staffGroupMap.put("Medical & Dental", set.getString("staffCount"));

            } else if (group.equalsIgnoreCase("NURSING_AND_MIDWIFERY_REGD")) {
                staffGroupMap.put("Nursing & Midwifery Registered", set.getString("staffCount"));

            } else if (group.equalsIgnoreCase("STUDENTS")) {
                staffGroupMap.put("Students", set.getString("staffCount"));

            }
        }
        return staffGroupMap;
    }

    public static List<Object> getVacancyIds(String employerCode, String vacancyDateFrom, String vacancyDateTo,
                                             String vacancyRef) throws IOException, SQLException {
        List<Object> list = new ArrayList<>();
        String query;
        if (vacancyRef == null) {
            query = "SELECT \"Vacancy ID\" FROM \"reporting-data_01\".mv_applicationexport_report WHERE \"Employer code\" = '"
                    + employerCode + "'  AND \"Date vacancy created\" BETWEEN '" + vacancyDateFrom + "' AND '"
                    + vacancyDateTo + "'";
        } else {
            query = "SELECT \"Vacancy ID\" FROM \"reporting-data_01\".mv_applicationexport_report WHERE \"Vacancy reference\" = '"
                    + vacancyRef + "'";
        }
        Connection conn = reportingDatabaseConnection();
        Statement statement = conn.createStatement();
        ResultSet set = statement.executeQuery(query);
        while (set.next()) {
            list.add(set.getString("Vacancy ID"));
        }
        set.next();

        return list;
    }

    private static String eoReportQuery(String categoryType, String vacancies) {
        return "SELECT " + categoryType + ", " + "COUNT(" + categoryType + ") "
                + "FROM \"reporting-data_01\".mv_common_application_application_information WHERE vacancy_id IN("
                + vacancies + ") "
                + "AND application_seq = '1' AND report_as_submitted IS NOT NULL AND report_as_shortlist IS NOT NULL AND report_as_interview IS NOT NULL AND report_as_offer IS NOT NULL "
                + "Group BY " + categoryType + " ";
    }

    public static Map<String, String> getEoReportRecords(String employerCode, String fromDate, String toDate,
                                                         String vacancyRef) throws IOException, SQLException {
        int totalCount = 0;
        ResultSet set = null;
        Map<String, String> eoReportsMap = new HashMap<>();
        Map<String, String> resultSetMap = new HashMap<>();
        List<Object> vacancyList = getVacancyIds(employerCode, fromDate, toDate, vacancyRef);
        String vacancies = vacancyList.toString().replace("[", "").replace("]", "").replaceAll("\'", "");
        String[] category = {"age_range_display", "sexuality_display", "ethnicity_display", "gender_display",
                "marital_status_display", "religion_display", "disability_display", "safeguarding_display",
                "physical_limitation_display",};
        for (String cat : category) {
            Connection conn = reportingDatabaseConnection();
            Statement statement = conn.createStatement();
            set = statement.executeQuery(eoReportQuery(cat, vacancies));
            while (set.next()) {
                eoReportsMap.put(set.getString(1), set.getString(2));
            }
        }
        if (eoReportsMap.get("24-44 years") != null) {
            resultSetMap.put("AgeBand_24-44", eoReportsMap.get("24-44 years"));
            totalCount = Integer.parseInt(eoReportsMap.get("24-44 years"));
        }
        if (eoReportsMap.get("45-59 years") != null) {
            resultSetMap.put("AgeBand_45-59", eoReportsMap.get("45-59 years"));
            totalCount += Integer.parseInt(eoReportsMap.get("45-59 years"));
        }
        if (eoReportsMap.get("60-74 years") != null) {
            resultSetMap.put("AgeBand_60-74", eoReportsMap.get("60-74 years"));
            totalCount += Integer.parseInt(eoReportsMap.get("60-74 years"));
        }
        if (eoReportsMap.get("75+ years") != null) {
            resultSetMap.put("AgeBand_75+", eoReportsMap.get("75+ years"));
            totalCount += Integer.parseInt(eoReportsMap.get("75+ years"));
        }
        if (eoReportsMap.get("Prefer not to say") != null) {
            resultSetMap.put("AgeBand_Prefer not to say", eoReportsMap.get("Prefer not to say"));
            totalCount += Integer.parseInt(eoReportsMap.get("Prefer not to say"));
            setTotal(totalCount);
        }
        if (eoReportsMap.get("Learning Disability/Difficulty") != null) {
            resultSetMap.put("Disability_Learning Disability/Difficulty",
                    eoReportsMap.get("Learning Disability/Difficulty"));
        }
        if (eoReportsMap.get("Long Standing Illness") != null) {
            resultSetMap.put("Disability_Long Standing Illness", eoReportsMap.get("Long Standing Illness"));
        }
        if (eoReportsMap.get("Mental Health Condition") != null) {
            resultSetMap.put("Disability_Mental Health Condition", eoReportsMap.get("Mental Health Condition"));
        }
        if (eoReportsMap.get("Other") != null) {
            resultSetMap.put("Disability_Other", eoReportsMap.get("Other"));
        }
        if (eoReportsMap.get("Physical Impairment") != null) {
            resultSetMap.put("Disability_Physical Impairment", eoReportsMap.get("Physical Impairment"));
        }
        if (eoReportsMap.get("Sensory Impairment") != null) {
            resultSetMap.put("Disability_Sensory Impairment", eoReportsMap.get("Sensory Impairment"));
        }
        if (eoReportsMap.get("Any other ethnic group") != null) {
            resultSetMap.put("Ethnicity_Any other ethnic group", eoReportsMap.get("Any other ethnic group"));
        }
        if (eoReportsMap.get("Any other white background") != null) {
            resultSetMap.put("Ethnicity_Any other white background", eoReportsMap.get("Any other white background"));
        }
        if (eoReportsMap.get("Arab") != null) {
            resultSetMap.put("Ethnicity_Arab", eoReportsMap.get("Arab"));
        }
        if (eoReportsMap.get("Asian/Asian British: Bangladeshi") != null) {
            resultSetMap.put("Ethnicity_Asian/Asian British: Bangladeshi",
                    eoReportsMap.get("Asian/Asian British: Bangladeshi"));
        }
        if (eoReportsMap.get("Asian/Asian British: Chinese") != null) {
            resultSetMap.put("Ethnicity_Asian/Asian British: Chinese",
                    eoReportsMap.get("Asian/Asian British: Chinese"));
        }
        if (eoReportsMap.get("Asian/Asian British: Indian") != null) {
            resultSetMap.put("Ethnicity_Asian/Asian British: Indian", eoReportsMap.get("Asian/Asian British: Indian"));
        }
        if (eoReportsMap.get("Asian/Asian British: Other") != null) {
            resultSetMap.put("Ethnicity_Asian/Asian British: Other", eoReportsMap.get("Asian/Asian British: Other"));
        }
        if (eoReportsMap.get("Asian/Asian British: Pakistani") != null) {
            resultSetMap.put("Ethnicity_Asian/Asian British: Pakistani",
                    eoReportsMap.get("Asian/Asian British: Pakistani"));
        }
        if (eoReportsMap.get("Black/Black British: African") != null) {
            resultSetMap.put("Ethnicity_Black/Black British: African",
                    eoReportsMap.get("Black/Black British: African"));
        }
        if (eoReportsMap.get("Black/Black British: Caribbean") != null) {
            resultSetMap.put("Ethnicity_Black/Black British: Caribbean",
                    eoReportsMap.get("Black/Black British: Caribbean"));
        }
        if (eoReportsMap.get("Black British: Other") != null) {
            resultSetMap.put("Ethnicity_Black/Black British: Other", eoReportsMap.get("Black British: Other"));
        }
        if (eoReportsMap.get("Mixed: Other") != null) {
            resultSetMap.put("Ethnicity_Mixed: Other", eoReportsMap.get("Mixed: Other"));
        }
        if (eoReportsMap.get("Mixed: White and Asian") != null) {
            resultSetMap.put("Ethnicity_Mixed: White and Asian", eoReportsMap.get("Mixed: White and Asian"));
        }
        if (eoReportsMap.get("Sensory Impairment") != null) {
            resultSetMap.put("Ethnicity_Mixed: White and Black African", eoReportsMap.get("Sensory Impairment"));
        }
        if (eoReportsMap.get("Mixed: White and Black Caribbean") != null) {
            resultSetMap.put("Ethnicity_Mixed: White and Black Caribbean",
                    eoReportsMap.get("Mixed: White and Black Caribbean"));
        }
        if (eoReportsMap.get("White: Irish") != null) {
            resultSetMap.put("Ethnicity_White: Irish", eoReportsMap.get("White: Irish"));
        }
        if (eoReportsMap.get("White: Traveller") != null) {
            resultSetMap.put("Ethnicity_White: Traveller", eoReportsMap.get("White: Traveller"));
        }
        if (eoReportsMap.get("Female") != null) {
            resultSetMap.put("Gender_Female", eoReportsMap.get("Female"));
        }
        if (eoReportsMap.get("Male") != null) {
            resultSetMap.put("Gender_Male", eoReportsMap.get("Male"));
        }
        if (eoReportsMap.get("Other") != null) {
            resultSetMap.put("Gender_Other", eoReportsMap.get("Other"));
        }
        if (eoReportsMap.get("Prefer not to say") != null) {
            resultSetMap.put("Gender_Prefer not to say", eoReportsMap.get("Prefer not to say"));
        }
        if (eoReportsMap.get("Civil Partnership") != null) {
            resultSetMap.put("Marital_Status_Civil Partnership", eoReportsMap.get("Civil Partnership"));
        }
        if (eoReportsMap.get("Divorced") != null) {
            resultSetMap.put("Marital_Status_Divorced", eoReportsMap.get("Divorced"));
        }
        if (eoReportsMap.get("Legally Separated") != null) {
            resultSetMap.put("Marital_Status_Legally Separated", eoReportsMap.get("Legally Separated"));
        }
        if (eoReportsMap.get("Married") != null) {
            resultSetMap.put("Marital_Status_Married", eoReportsMap.get("Married"));
        }
        if (eoReportsMap.get("Prefer not to say") != null) {
            resultSetMap.put("Marital_Status_Prefer not to say", eoReportsMap.get("Prefer not to say"));
        }
        if (eoReportsMap.get("Single") != null) {
            resultSetMap.put("Marital_Status_Single", eoReportsMap.get("Single"));
        }
        if (eoReportsMap.get("Widowed") != null) {
            resultSetMap.put("Marital_Status_Widowed", eoReportsMap.get("Widowed"));
        }
        if (eoReportsMap.get("Any other religion") != null) {
            resultSetMap.put("Religion_Any other religion", eoReportsMap.get("Any other religion"));
        }
        if (eoReportsMap.get("Atheism/no religion") != null) {
            resultSetMap.put("Religion_Atheism/no religion", eoReportsMap.get("Atheism/no religion"));
        }
        if (eoReportsMap.get("Buddhism") != null) {
            resultSetMap.put("Religion_Buddhism", eoReportsMap.get("Buddhism"));
        }
        if (eoReportsMap.get("Hinduism") != null) {
            resultSetMap.put("Religion_Hinduism", eoReportsMap.get("Hinduism"));
        }
        if (eoReportsMap.get("Jainism") != null) {
            resultSetMap.put("Religion_Jainism", eoReportsMap.get("Jainism"));
        }
        if (eoReportsMap.get("Judaism") != null) {
            resultSetMap.put("Religion_Judaism", eoReportsMap.get("Judaism"));
        }
        if (eoReportsMap.get("Prefer not to say") != null) {
            resultSetMap.put("Religion_Prefer not to say", eoReportsMap.get("Prefer not to say"));
        }
        if (eoReportsMap.get("Sikhism") != null) {
            resultSetMap.put("Religion_Sikhism", eoReportsMap.get("Sikhism"));
        }
        if (eoReportsMap.get("Bisexual") != null) {
            resultSetMap.put("Sexual_Orientation_Bisexual", eoReportsMap.get("Bisexual"));
        }
        if (eoReportsMap.get("Gay/Lesbian") != null) {
            resultSetMap.put("Sexual_Orientation_Gay/Lesbian", eoReportsMap.get("Gay/Lesbian"));
        }
        if (eoReportsMap.get("Hetrosexual/Straight") != null) {
            resultSetMap.put("Sexual_Orientation_Hetrosexual/Straight", eoReportsMap.get("Hetrosexual/Straight"));
        }
        if (eoReportsMap.get("Other sexual orientation not listed") != null) {
            resultSetMap.put("Sexual_Orientation_Other sexual orientation not listed",
                    eoReportsMap.get("Other sexual orientation not listed"));
        }
        if (eoReportsMap.get("Prefer not to say") != null) {
            resultSetMap.put("Sexual_Orientation_Prefer not to say", eoReportsMap.get("Prefer not to say"));
        }
        if (eoReportsMap.get("Undecided") != null) {
            resultSetMap.put("Sexual_Orientation_Undecided", eoReportsMap.get("Undecided"));
        }

        return resultSetMap;
    }

    public static Map<String, String> getTimeToHireReportData(String employerCode, String vacancyDateFrom,
                                                              String vacancyDateTo) throws IOException, SQLException {
        Map<String, String> timeToHireMap = new HashMap<>();
        Map<String, String> dbResultMap = new HashMap<>();
        String query = "SELECT DISTINCT ON (report_as_staff_group, \"Date vacancy published\", \"Date vacancy advert closed\", \"Date vacancy shortlisting complete\", \"Date first applicant offer sent\",\"Applicant start date\") report_as_staff_group, \"Date vacancy published\", \"Date vacancy advert closed\", \"Date vacancy shortlisting complete\" \"Date first applicant offer sent\",\"Applicant start date\" "
                + "FROM \"reporting-data_01\".mv_applicationexport_report where \"Date vacancy created\" between '"
                + vacancyDateFrom + "' and '" + vacancyDateTo + "' AND \"Employer code\" = '" + employerCode
                + "' AND report_as_open IS NOT NULL AND report_as_closed IS NOT NULL";
        Connection conn = reportingDatabaseConnection();
        Statement statement = conn.createStatement();
        ResultSet set = statement.executeQuery(query);
        while (set.next()) {
            timeToHireMap.put(set.getString(1), set.getString(2));
            if (timeToHireMap.get("ALLIED_HEALTH_PROF") != null) {
                dbResultMap.put("Allied Health Professionals", timeToHireMap.get("ALLIED_HEALTH_PROF"));
            } else {
                dbResultMap.put("Allied Health Professionals", "0");
            }
            if (timeToHireMap.get("CLINICAL_SERVICES") != null) {
                dbResultMap.put("Additional Clinical Services", timeToHireMap.get("CLINICAL_SERVICES"));
            } else {
                dbResultMap.put("Additional Clinical Services", "0");
            }
            if (timeToHireMap.get("PROF_SCIENTIFIC_AND_TECHNICAL") != null) {
                dbResultMap.put("Additional Professional Scientific & Technical",
                        timeToHireMap.get("PROF_SCIENTIFIC_AND_TECHNICAL"));
            } else {
                dbResultMap.put("Additional Professional Scientific & Technical", "0");
            }
            if (timeToHireMap.get("ADMINISTRATIVE_AND_CLERICAL") != null) {
                dbResultMap.put("Administrative & Clerical", timeToHireMap.get("ADMINISTRATIVE_AND_CLERICAL"));
            } else {
                dbResultMap.put("Administrative & Clerical", "0");
            }
            if (timeToHireMap.get("ALLIED_HEALTH_PROF") != null) {
                dbResultMap.put("Allied Health Professionals", timeToHireMap.get("ALLIED_HEALTH_PROF"));
            } else {
                dbResultMap.put("Allied Health Professionals", "0");
            }
            if (timeToHireMap.get("ESTATES_AND_ACILLARY") != null) {
                dbResultMap.put("Estates & Ancillary", timeToHireMap.get("ESTATES_AND_ACILLARY"));
            } else {
                dbResultMap.put("Estates & Ancillary", "0");
            }
            if (timeToHireMap.get("ESTATES_AND_ACILLARY") != null) {
                dbResultMap.put("Estates & Ancillary", timeToHireMap.get("ESTATES_AND_ACILLARY"));
            } else {
                dbResultMap.put("Estates & Ancillary", "0");
            }
            if (timeToHireMap.get("HEALTHCARE_SCIENTISTS") != null) {
                dbResultMap.put("Healthcare Scientists", timeToHireMap.get("HEALTHCARE_SCIENTISTS"));
            } else {
                dbResultMap.put("Healthcare Scientists", "0");
            }
            if (timeToHireMap.get("MEDICAL_AND_DENTAL") != null) {
                dbResultMap.put("Medical & Dental", timeToHireMap.get("MEDICAL_AND_DENTAL"));
            } else {
                dbResultMap.put("Medical & Dental", "0");
            }
            if (timeToHireMap.get("NURSING_AND_MIDWIFERY_REGD") != null) {
                dbResultMap.put("Nursing & Midwifery Registered", timeToHireMap.get("NURSING_AND_MIDWIFERY_REGD"));
            } else {
                dbResultMap.put("Nursing & Midwifery Registered", "0");
            }
            if (timeToHireMap.get("STUDENTS") != null) {
                dbResultMap.put("Students", timeToHireMap.get("STUDENTS"));
            } else {
                dbResultMap.put("Students", "0");
            }
        }
        return dbResultMap;
    }
}