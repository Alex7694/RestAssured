package Files;

public class PayLoad {

    public static String getPostData() {

        String bodyParameters = "{\n" +
                "\n" +
                "    \"location\":{\n" +
                "\n" +
                "        \"lat\" : -38.383494,\n" +
                "\n" +
                "        \"lng\" : 33.427362\n" +
                "\n" +
                "    },\n" +
                "\n" +
                "    \"accuracy\":50,\n" +
                "\n" +
                "    \"name\":\"Frontline house\",\n" +
                "\n" +
                "    \"phone_number\":\"(+91) 983 893 3937\",\n" +
                "\n" +
                "    \"address\" : \"29, side layout, cohen 09\",\n" +
                "\n" +
                "    \"types\": [\"shoe park\",\"shop\"],\n" +
                "\n" +
                "    \"website\" : \"http://google.com\",\n" +
                "\n" +
                "    \"language\" : \"French-IN\"\n" +
                "\n" +
                "}";

        return bodyParameters;
    }

    public static String Addbook(String aisle, String isbn) {

        String payLoad = "{\r\n\"name\": \"Learn Appium Automation with Java\",\r\n\"isbn\": \""+isbn+"\",\r\n\"aisle\": \""+aisle+"\",\r\n\"author\": \"John foe\"\r\n}";

        return payLoad;
    }

    public static String getJiraAuthBody() {
        String payload = "{ \"username\": \"Alex\", \"password\": \"12345\" }";
        return  payload;
    }

    public static String getCreateBugBody() {
        String payload = "{\n" +
                "    \"fields\": {\n" +
                "       \"project\":\n" +
                "       {\n" +
                "          \"key\": \"ZAVTRACH19\"\n" +
                "       },\n" +
                "       \"summary\": \"Fourthssssssssssscf2ssssssssss bug\",\n" +
                "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\n" +
                "       \"issuetype\": {\n" +
                "          \"name\": \"Ошибка\"\n" +
                "       }\n" +
                "   }\n" +
                "}";

        return payload;
    }

    public static String getCreateCommentBugBody() {
        String payload = "{\n" +
                "   \"body\": \"New Comment111111111111111111111\",\n" +
                "   \"visibility\": {\n" +
                "       \"type\": \"role\",\n" +
                "       \"value\": \"Administrators\"\n" +
                "   }\n" +
                "}";

        return payload;
    }

    public static String getUpdateCommentBugBody() {
        String payload = "{\n" +
                "   \"body\": \"Update Comment\",\n" +
                "   \"visibility\": {\n" +
                "       \"type\": \"role\",\n" +
                "       \"value\": \"Administrators\"\n" +
                "   }\n" +
                "}";

        return payload;
    }
}
