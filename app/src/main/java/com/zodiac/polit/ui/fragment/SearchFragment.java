package com.zodiac.polit.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.minilive.library.adapter.BaseViewHolderHelper;
import com.minilive.library.adapter.recycler.BaseRecyclerAdapter;
import com.minilive.library.adapter.recycler.widget.RecyclerDivider;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.minilive.library.util.Trace;
import com.zodiac.polit.Constant;
import com.zodiac.polit.R;
import com.zodiac.polit.bean.response.ArticleResponse;
import com.zodiac.polit.http.provider.HomeProvider;
import com.zodiac.polit.ui.WebActivity;
import com.zodiac.polit.ui.fragment.home.MessageFragment;
import com.zodiac.polit.util.HtmlUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class SearchFragment extends BaseRecyclerFragment {

    private List<ArticleResponse.PageBean.ListBean> mList = new ArrayList<>();

    private String key;

    public void setKey(String key) {
        this.key = key;
        onRefresh(refreshLayout);
    }

    @Override
    protected void initLayoutManager() {
        super.initLayoutManager();
        recyclerView.addItemDecoration(RecyclerDivider.newShapeDivider());
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new SearchAdapter(recyclerView);
    }

    @Override
    protected void loadData() {
        HomeProvider.search(key, mCurrentPage, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
                showToast(e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();

                Trace.d("search" , "res----------" + response);


                if (StringUtils.isEmpty(response)){
                    showToast("暂无数据");
                    return;
                }

                ArticleResponse mArticleResponse = new Gson().fromJson(response, ArticleResponse.class);
                if (mArticleResponse == null){
                    showToast("暂无数据");
                    return;
                }

                if (mArticleResponse.getList() == null){
                    showToast("暂无数据");
                    return;
                }

                if (mArticleResponse.getPage() == null){
                    showToast("暂无数据");
                    return;
                }

                if (mArticleResponse.getPage().getList() == null){
                    showToast("暂无数据");
                    return;
                }

                if (mArticleResponse.getList().size() < HomeProvider.LIMIT){
                    refreshLayout.setEnableLoadMore(false);
                }

                if (mCurrentPage == 1){
                    mList.clear();
                }
                mList.addAll(mArticleResponse.getPage().getList());
                if (mCurrentPage == 1){
                    mAdapter.setData(mArticleResponse.getList());
                } else {
                    mAdapter.addMoreData(mArticleResponse.getList());
                }

            }
        });
    }


    class SearchAdapter extends BaseRecyclerAdapter<ArticleResponse.ListBeanX>{

        private int mItemSize;

        public SearchAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_policy);
            mItemSize = getResources().getDimensionPixelSize(R.dimen.dimen_70dp);
        }

        @Override
        protected void fillData(BaseViewHolderHelper helper, int position, final ArticleResponse.ListBeanX model) {
            final ArticleResponse.PageBean.ListBean listBean = mList.get(position);
            ImageView imageView = helper.getImageView(R.id.ivNews);
            if (StringUtils.isEmpty(listBean.getImageSrc())){
                imageView.setVisibility(View.GONE);
            } else {
                imageView.setVisibility(View.VISIBLE);
                Glide.with(SearchFragment.this).load(Constant.BASEURL + listBean.getImageSrc())
                        .into(imageView);
            }

            final TextView tvContent = helper.getView(R.id.tvContent);

            helper.setText(R.id.tvTitle , listBean.getTitle())
                    .setText(R.id.tvTime , listBean.getCreateDate());

            helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Bundle bundle = new Bundle();
                    bundle.putString(NewsDetailActivity.KEY_CATEGORY , listBean.getCategory().getId());
                    bundle.putString(NewsDetailActivity.KEY_CONTENT , listBean.getId());
                    jumpTo(NewsDetailActivity.class , bundle);*/
                    Bundle bundle = new Bundle();
                    bundle.putString(WebActivity.KEY_WEB_URL , HtmlUtil.getDetailUrl(listBean.getId() , listBean.getCategory().getId()));
                    jumpTo(WebActivity.class , bundle);
                }
            });
            String content = HtmlUtil.getHtmlText(model.getContent());
            setText(tvContent , content);

            LinearLayout linearLayout = helper.getView(R.id.layoutItem);

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
            if (StringUtils.isEmpty(content)){
                layoutParams.height = mItemSize;
            } else {
                layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            }
            linearLayout.setLayoutParams(layoutParams);
            /*tvContent.post(new Runnable() {
                @Override
                public void run() {
                    tvContent.setText("");
                    showDataSync(model.getContent(), tvContent);
                }
            });*/

        }
    }

}
