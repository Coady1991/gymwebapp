import play.*;
import play.jobs.*;
import play.test.*;
import models.*;

/**
 * Created by Coady on 28/04/2017.
 */
@OnApplicationStart
public class Bootstrap extends Job
{
  public void doJob()
  {
    if(Member.count() == 0)
    {
      Fixtures.delete();
      Fixtures.loadModels("data.yml");
    }
  }
}
