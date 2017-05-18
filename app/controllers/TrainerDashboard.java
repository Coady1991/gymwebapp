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
    Logger.info("Rendering TrainerDashboard");
    Trainer trainer = Accounts.getLoggedInTrainer();
    List<Member> members = trainer.members;
    render("trainerdashboard.html", trainer, members);
  }

  public static void deleteMember(Long memberid)
  {
    Trainer trainer = Accounts.getLoggedInTrainer();
    Member member = Member.findById(memberid);
    trainer.members.remove(member);
    trainer.save();
    member.delete();
    Logger.info("Member deleted");
    redirect("/trainerdashboard");
  }

  public static void memberDashboard(Long memberid)
  {
    Logger.info("Rendering Dashboard");
    Trainer trainer = Accounts.getLoggedInTrainer();
    Member member = Member.findById(memberid);
    List<Assessment> assessments = member.assessments;
    render("trainerview.html", trainer, member, assessments);
  }
}
