package sh.calaba.instrumentationbackend.actions.list;


import java.util.LinkedList;
import java.util.List;

import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.TestHelpers;
import sh.calaba.instrumentationbackend.actions.Action;
import android.view.View;
import android.widget.ListView;

/**
 * * args:
 * <ul>
 * <li>id of view</li>
 * </ul>
 * In ruby, we can then parse the response:
 * 
 * <pre>
 *         size = result['bonusInformation'].first
 * </pre>
 * 
 * @return <code>bonusInformation</code> contains the size of the list view
 * @author dpreussler
 */
public class GetListSize implements Action {

    @Override
    public Result execute(String... args) {
        System.out.println("args: " + args.length);
        final View view = TestHelpers.getViewById(args[0]);
        if (view == null) {
            return new Result(false, "No view found with id: '" + args[0] + "'");
        } else if (!(view instanceof ListView)) {
            return new Result(false, "Expected ListView found: '" + view.getClass() + "'");
        } else {
            Result result = new Result(true);
            List<String> extras = new LinkedList<String>();
            extras.add(String.valueOf(((ListView) view).getAdapter().getCount()));
            result.setExtras(extras);
            return result;
        }
    }

    @Override
    public String key() {
        return "get_list_size";
    }

}
