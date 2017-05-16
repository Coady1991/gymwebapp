package controllers;

import java.util.List;

import models.*;
import play.mvc.Controller;

/**
 * Created by Coady on 13/05/2017.
 */
public class Admin extends Controller
{
  public static void index()
  {
    List<Assessment> member = Assessment.findAll();
    render("admin.html", member);
  }
}