package controllers;

import play.Logger;
import play.mvc.Controller;
import models.*;
import java.util.List;


/**
 * Created by Coady on 17/05/2017.
 */
public class TrainerDashboard extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Dashboard");
    Trainer trainer = Accounts.getLoggedInTrainer();
    List<Member> members = trainer.members;
    render("trainerdashboard.html", trainer, members);
  }
}
