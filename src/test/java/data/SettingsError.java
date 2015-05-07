package data;

/**
 * Created by techmagic on 04.05.15.
 */
public class SettingsError {

   String notActivatedAccountError = "We've noticed that you have not activated your account. It is important that you activate your account so that you can get the most out of Areobooks. We have sent you an email containing the activation link.";

   String successProfileUpdate = "Your profile has been updated";


   public String getNotActivatedAccountError(){
       return notActivatedAccountError;
   }
   public String getSubmitSuccessMessage(){
       return successProfileUpdate;
   }
    public String getEmailModificationWarning(String email){
        return  "You need to confirm your email change. A confirmation email was sent to "+email+". Your email will not be changed until you complete this step!";
    }

}
