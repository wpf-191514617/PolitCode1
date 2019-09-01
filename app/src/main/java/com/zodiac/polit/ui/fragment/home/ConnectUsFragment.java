package com.zodiac.polit.ui.fragment.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.minilive.library.adapter.BaseViewHolderHelper;
import com.minilive.library.adapter.recycler.BaseRecyclerAdapter;
import com.minilive.library.adapter.recycler.OnRecyclerItemClickListener;
import com.minilive.library.adapter.recycler.widget.RecyclerDivider;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.minilive.library.util.Trace;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zodiac.polit.R;
import com.zodiac.polit.bean.response.ConnectUsResponse;
import com.zodiac.polit.http.provider.HomeProvider;
import com.zodiac.polit.ui.fragment.BaseRecyclerFragment;
import com.zodiac.polit.view.CodeDialog;

import java.util.List;

import okhttp3.Call;

/**
 * Created by john on 2018/9/24.
 */

public class ConnectUsFragment extends BaseRecyclerFragment{

    private AlertDialog mItemDialog;

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new ConnectUsListAdapter(recyclerView);
    }

    @Override
    protected void initLayoutManager() {
        super.initLayoutManager();
        recyclerView.addItemDecoration(RecyclerDivider.newShapeDivider());
        refreshLayout.setEnableLoadMore(false);
        mAdapter.setOnRVItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View itemView, int position) {
                ConnectUsResponse connectUsResponse = (ConnectUsResponse) mAdapter.getItem(position);
               // showItemDialog(connectUsResponse);
                callPhone(connectUsResponse);
            }
        });
    }

    private String[] mItems = {"拨打电话","发送邮件"};

    private void showItemDialog(final ConnectUsResponse item) {
       // if (mItemDialog == null){
            mItemDialog = new AlertDialog.Builder(getActivity()).setItems(mItems, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    if (i == 0){
                        callPhone(item);
                    } else {
                        sendEmail(item);
                    }
                }
            }).create();
       // }
        mItemDialog.show();
    }

    private void sendEmail(ConnectUsResponse item) {
        String[] email = {item.getEmail()}; // 需要注意，email必须以数组形式传入
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822"); // 设置邮件格式
        intent.putExtra(Intent.EXTRA_EMAIL, email); // 接收人
        intent.putExtra(Intent.EXTRA_CC, ""); // 抄送人
        intent.putExtra(Intent.EXTRA_SUBJECT, ""); // 主题
        intent.putExtra(Intent.EXTRA_TEXT, ""); // 正文
        startActivity(Intent.createChooser(intent, ""));
    }

    private void callPhone(ConnectUsResponse item) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + item.getPhone());
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    protected void loadData() {
        HomeProvider.loadConnectUsData(new StringCallback() {
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
                if (!StringUtils.isEmpty(response)){
                    List<ConnectUsResponse> responseList = new Gson().fromJson(response ,
                            new TypeToken<List<ConnectUsResponse>>(){}.getType());
                    mAdapter.setData(responseList);
                }
            }
        });
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCurrentPage = 1;
        loadData();
    }

    private class ConnectUsListAdapter extends BaseRecyclerAdapter<ConnectUsResponse>{

        public ConnectUsListAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_connectus);
        }

        @Override
        protected void fillData(BaseViewHolderHelper helper, final int position, ConnectUsResponse model) {
            helper.setText(R.id.tvMater , model.getMaster())
                    .setText(R.id.tvName , model.getName())
                    .setText(R.id.tvPhone , model.getPhone())
                    .setText(R.id.tvEMail,model.getEmail())
                    .setText(R.id.tvAddress , model.getAddress())
                    .setText(R.id.tvProvince , model.getProvince());
            if (position == 0 || position==getData().size()-1){
                helper.getView(R.id.layoutProvice).setVisibility(View.GONE);
            } else {
                helper.getView(R.id.layoutProvice).setVisibility(View.VISIBLE);
            }

            final ImageView ivCode = helper.getImageView(R.id.ivCode);
            if (position == 1){
                ivCode.setImageResource(R.drawable.weixin_shenyang);
                ivCode.setVisibility(View.VISIBLE);
            } else if (position == 2){
                ivCode.setImageResource(R.drawable.weixin_beijing);
                ivCode.setVisibility(View.VISIBLE);
            }else if (position == 3){
                ivCode.setVisibility(View.GONE);
            } else if (position == 4){
                ivCode.setImageResource(R.drawable.weixin_jinan);
                ivCode.setVisibility(View.VISIBLE);
            }else if (position == 5){
                //ivCode.setImageResource(R.drawable.weixin_beijing);
                //ivCode.setVisibility(View.VISIBLE);
                ivCode.setVisibility(View.GONE);
            } else if (position == 6){
                ivCode.setImageResource(R.drawable.weixin_guangzhou);
                ivCode.setVisibility(View.VISIBLE);
            }else{
                //ivCode.setImageResource(R.drawable.weixin_);
                //ivCode.setVisibility(View.VISIBLE);
                ivCode.setVisibility(View.GONE);
            }

            ivCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showCodeDialog(position);
                }
            });

        }
    }


    private CodeDialog mCodeDialog;

    private void showCodeDialog(int position){
        if (mCodeDialog == null){
            mCodeDialog = new CodeDialog(getActivity());
        }
        int res = -1;
        if (position == 1){
            res = R.drawable.weixin_shenyang;
        } else if (position == 2){
            res = R.drawable.weixin_beijing;
        } else if (position == 4){
            res = R.drawable.weixin_jinan;
        }else if (position == 6){
            res = R.drawable.weixin_guangzhou;
        }

        mCodeDialog.setImage(res);
        mCodeDialog.show();
    }

}
