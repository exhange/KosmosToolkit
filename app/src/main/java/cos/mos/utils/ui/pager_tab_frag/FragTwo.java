package cos.mos.utils.ui.pager_tab_frag;

import android.support.v4.content.ContextCompat;

import cos.mos.utils.R;
import cos.mos.toolkit.init.KFragment;

/**
 * @Description: <p>
 * @Author: Kosmos
 * @Date: 2018.11.20 15:43
 * @Email: KosmoSakura@gmail.com
 */
public class FragTwo extends KFragment {
    @Override
    protected int layout() {
        return R.layout.frag_one;
    }

    @Override
    protected void init() {
        findViewById(R.id.frag_bg).setBackgroundColor(ContextCompat.getColor(context, R.color.yellow));
    }

    @Override
    protected void logic() {

    }
}
