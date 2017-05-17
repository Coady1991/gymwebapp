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
    double BMI = Analytics.calculateBMI(member, member.assessments.get(assessments.size() - 1));
    String bmiCategory = Analytics.determineBMICategory(BMI);
    String idealWeight = Analytics.idealWeight(bmiCategory);
    render ("dashboard.html", member, assessments, BMI, bmiCategory, idealWeight);
  }

  public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist, double hips)
  {
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
    member.assessments.add(assessment);
    member.save();
    Logger.info("Adding Assessment" + weight, chest, thigh, upperArm, waist, hips);
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
