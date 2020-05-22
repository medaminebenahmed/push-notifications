import com.proxym.notification.api.FirebaseMessagingService;
import com.proxym.notification.model.DataNotification;
import com.proxym.notification.model.FirebaseMessage;
import com.proxym.notification.model.MetaData;
import com.proxym.notification.model.Notification;

/**
 * Created by med-amine.ben-ahmed on 16/05/2019.
 */
public class Test {


    private final static String SEVER_KEY = "AAAACy1ICUA:APA91bFGo10FzNffmXP4_x8_I1afyAr8GWNiizEj9SmFqRViPe7D3bQUhGP66FEQet0k5migUvpQ2tVq-HNriRSbHW4z25HcBdOkkWtA03mjY-v8CP99AuoN2GjLA5CBMA17ROY4Zyxd";
    private final static String TOKEN = "eyxI8VB2RsU:APA91bHw65wG5i5m1UT2unjkTjRBQiUXX-ovhFG4QqnJTjo1xsKycE74txynzBGPzX8BAqV7x2xBvyVZK0gvXFShyJx1kXApeGvFHUpZL1Ojko2yxCKX24ybnOOlmT5Vk7c58FXwkmZf";
    private final static String TOKEN_GRP = "APA91bEpTNfbOObje8X-yqADXsN_gMl2haot0OJQ10yNTzJjLvebPzWAP4cQJPVCv4cYQipghdv6nhVafysE68Z056VuQZfO1f8bdsFwDMd1g1_AiLy7wVDaudXLoxX9amsyiytsic8g";


    public static void main(String[] args) {

        FirebaseMessage firebaseMessage = new FirebaseMessage();
        Notification notification = new Notification();
        notification.setTitle("hello it's me amine");
        notification.setBody("Bankerise Notification");
        firebaseMessage.setTo(TOKEN);
        firebaseMessage.setTo("/topics/Bankerise123");
        firebaseMessage.setNotification(notification);


       FirebaseMessagingService.getInstance(SEVER_KEY).sendNotification(firebaseMessage);
        FirebaseMessagingService.getInstance(SEVER_KEY).validateToken(TOKEN);
//        com.proxym.notification.api.FirebaseMessagingService.getInstance(SEVER_KEY).addDevicesGroup();
//        List<String> tokens = new ArrayList<>();
//       tokens.add(TOKEN);
//       tokens.add(TOKEN);
////        com.proxym.notification.api.FirebaseMessagingService.getInstance(SEVER_KEY).createGroup("bankerise","groupForTest",tokens);
//        com.proxym.notification.api.FirebaseMessagingService.getInstance(SEVER_KEY).createTopic("BankeriseTestTopic3", tokens);

    }
}
