package controllers;

import play.Logger;
import play.mvc.Controller;

public class Start extends Controller
{
  /**
   * Renders the start page when the app is first initialized.
   */
  public static void index() {
    Logger.info("Rendering Start");
    render ("start.html");
  }
}
