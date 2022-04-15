package Files;

import Basics.Jira.AddComment;
import Basics.Jira.CreateBug;

public class Resourses {

    public static String placePostData() {
        String resourse = "/maps/api/place/add/json";
        return resourse;
    }

    public static String getJiraResourse() {
        String resourse = "/rest/auth/1/session";
        return resourse;
    }

    public static String getCreateBugResourse() {
        String resourse = "/rest/api/2/issue/";
        return resourse;
    }

    CreateBug createBug = new CreateBug();

    public static String getCreateBugCommentResourse(String bugId) {
        String resourse = "/rest/api/2/issue/"+ bugId+"/comment/";
        System.out.println(resourse);
        return resourse;
    }

    public static String getUpdateCommentResourse(String bugId, String commentId) {
        String resourse = "/rest/api/2/issue/"+bugId+"/comment/"+commentId+"";
        System.out.println(resourse);
        return resourse;
    }

}


