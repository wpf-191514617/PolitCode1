package com.zodiac.polit.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.minilive.library.util.StringUtils;
import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseActivity;
import com.zodiac.polit.http.provider.HomeProvider;
import com.zodiac.polit.ui.fragment.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.tvSearch)
    TextView tvSearch;

    private SearchFragment mSearchFragment;

    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void initViewAndData() {
        setTitle("信息检索");
        mSearchFragment = new SearchFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.flContent,mSearchFragment).commit();
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tvSearch)
    public void onViewClicked() {
        String key = etSearch.getText().toString();
        if (StringUtils.isEmpty(key)){
            showToast("请输入要搜索的内容");
            return;
        }
        onSearch();
    }

    private void onSearch() {
        mSearchFragment.setKey(etSearch.getText().toString());
    }
}
