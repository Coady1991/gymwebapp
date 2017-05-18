package controllers;

import models.*;

import play.Logger;
import play.mvc.Controller;
import utils.Analytics;

import java.util.List;

public class Dashboard extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessments = member.assessments;
    double BMI = Analytics.calculateBMI(member);
    String bmiCategory = Analytics.determineBMICategory(BMI);
    String isIdealBodyWeight = Analytics.isIdealBodyWeight(member);
    render ("dashboard.html", member, assessments, BMI, bmiCategory, isIdealBodyWeight);
  }

  public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist, double hips, String comment)
  {
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips, comment);
    member.assessments.add(assessment);
    member.save();
    Logger.info("Adding Assessment" + weight, chest, thigh, upperArm, waist, hips, comment);
    redirect("/dashboard");
  }

  public static void deleteAssessment(Long id, Long assessmentid)
  {
    Member member = Member.findById(id);
    Assessment assessment = Assessment.findById(assessmentid);
    member.assessments.remove(assessment);
    member.save();
    assessment.delete();
    Logger.info("Deleting " + assessment);
    redirect("/dashboard");
  }
}
