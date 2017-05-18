package controllers;

import models.*;

import play.Logger;
import play.mvc.Controller;
import utils.Analytics;

import java.util.List;

/**
 * The class controls the methods that can be used by a member
 * in their dashboard.
 */
public class Dashboard extends Controller
{
  /**
   * When a member logs in this method renders their dashboard
   * and associated details about that member.
   */
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

  /**
   * This method allows a member to add an assessment.
   * But they are not allowed to add a comment.
   *
   * @param weight The members weight.
   * @param chest The members chest size.
   * @param thigh The members thigh size.
   * @param upperArm The members upper arm size.
   * @param waist The members waist size.
   * @param hips The members hip size.
   * @param comment This will be completed by the trainer.
   */
  public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist, double hips, String comment)
  {
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips, comment);
    member.assessments.add(assessment);
    member.save();
    Logger.info("Adding Assessment" + weight, chest, thigh, upperArm, waist, hips, comment);
    redirect("/dashboard");
  }

  /**
   * This method allows the member to delete an assessment.
   *
   * @param id The current logged in members id.
   * @param assessmentid The id of the assessment they wish to delete.
   */
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
