/**
 * Created with IntelliJ IDEA.
 * User: vshivamurthy
 * Date: 9/12/13
 * Time: 10:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class SummaryType {

    public Summarize getSummaryType(String type)
    {
        if(type.contentEquals("run"))
        {
            return new SummarizeRun();
        }
        else if(type.contentEquals("gesture"))
        {
            return new SummarizeGesture();
        }
        else if(type.contentEquals("gesturesub"))
        {
            return new SummarizeGestureSub();
        }
        return null;
    }


}
