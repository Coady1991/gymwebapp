import play.*;
import play.jobs.*;
import play.test.*;

/**
 * Created by Coady on 28/04/2017.
 */
public class Bootstrap extends Job
{
  public void doJob()
  {
    Fixtures.loadModels("data.yml");
  }
}
