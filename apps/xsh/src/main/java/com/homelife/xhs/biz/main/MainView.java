package com.homelife.xhs.biz.main;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import com.homelife.xhs.R;
import com.homelife.xhs.bean.Satin;
import com.homelife.xhs.recyclerview.ViewType;
import com.homelife.xhs.recyclerview.common.AdapterItem;
import com.homelife.xhs.recyclerview.common.BaseAdapter;
import com.homelife.xhs.recyclerview.common.BaseViewHolder;
import com.homelife.xhs.recyclerview.itemDecoration.SpaceItemDecoration;
import com.homelife.xhs.recyclerview.items.SatinItem;
import com.homelife.xhs.recyclerview.view.PullLoadHeadFootGridRecyclerView;
import com.homelife.xhs.recyclerview.viewholder.HeadSatinViewHolder;
import com.homelife.xhs.recyclerview.viewholder.SatinViewHolder;
import com.homelife.net.RxException;
import com.homelife.util.DisplayTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Description 主页View的UI处理
 * @Author Xue
 * @CreateDate
 */
public class MainView extends MainContract.View{

    @BindView(R.id.pull_load_recycler_view)
    PullLoadHeadFootGridRecyclerView mPullLoadRecyclerView;//小区

    private View mHeaderView;


    private Context mContext;
    private MainPresenter mPresenter;

    private List<AdapterItem> mList = new ArrayList<>();
    private List<AdapterItem> mHeadList = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mHeadAdapter;
    private int mPage = 1;
    private RecyclerView mHeadRecyclerView;


    public MainView(Context mContext, View view, MainPresenter presenter) {
        this.mContext = mContext;
        this.mPresenter = presenter;
        this.setView(view); //使用ButterKnife必须调用该方法
    }


    @Override
    public void initView() {
        mHeaderView = LayoutInflater.from(mContext).inflate(R.layout.fragment_rec_head, null);

        mHeadRecyclerView = mHeaderView.findViewById(R.id.head_recycler_view);


        SparseArray<Class<? extends BaseViewHolder>> viewHolders2 = new SparseArray<>();
        viewHolders2.put(ViewType.TYPE_Satin, HeadSatinViewHolder.class);
        mHeadAdapter = new BaseAdapter(mHeadList, mContext, viewHolders2);
        mHeadRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mHeadRecyclerView.setAdapter(mHeadAdapter);


        SparseArray<Class<? extends BaseViewHolder>> viewHolders = new SparseArray<>();
        viewHolders.put(ViewType.TYPE_Satin, SatinViewHolder.class);
        mAdapter = new BaseAdapter(mList, mContext, viewHolders);
        mPullLoadRecyclerView.setStaggeredGridLayout(2);
        mPullLoadRecyclerView.setAdapter(mAdapter);
        mPullLoadRecyclerView.addHeadView(mHeaderView);
        mPullLoadRecyclerView.addItemDecoration(new SpaceItemDecoration(new DisplayTool(mContext).dip2px(12), new DisplayTool(mContext).dip2px(10), new DisplayTool(mContext).dip2px(5), 1, SpaceItemDecoration.STAGGEREDGRIDLAYOUT));


        initData();

    }

    /**
     * @Description 初始化数据
     * @Author Xue
     * @CreateDate 2018/11/23 上午2:10
     */
    public void initData() {
        mPullLoadRecyclerView.setOnPullLoadMoreListener(new PullLoadHeadFootGridRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                mPresenter.getSatin(mPage);

            }

            @Override
            public void onLoadMore() {
                mPage++;
                mPresenter.getSatin(mPage);
            }
        });

        mPullLoadRecyclerView.setRefreshing(true);


    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void setSatin(List<Satin> list) {

        if (mPage == 1) {
            mPullLoadRecyclerView.setNoMore(false);
            mList.clear();
            mHeadList.clear();
            if (list == null || list.size() == 0) {
                mPullLoadRecyclerView.setNoMore("还没有新闻");
            } else {

                List<AdapterItem> adapterItems = new ArrayList<>();
                for (Satin satin : list) {
                    adapterItems.add(new SatinItem(satin));
                }

                //头部的recyclerview数据刷新
                mHeadList.addAll(adapterItems);
                mHeadAdapter.notifyDataSetChanged();

                mList.addAll(adapterItems);
                mPullLoadRecyclerView.setPullLoadMoreCompleted();
            }
        } else {
            if (list == null || list.size() == 0) {
                mPullLoadRecyclerView.setNoMore("没有更多新闻");
            } else {
                List<AdapterItem> adapterItems = new ArrayList<>();
                for (Satin satin : list) {
                    adapterItems.add(new SatinItem(satin));
                }
                mList.addAll(adapterItems);
                mPullLoadRecyclerView.setPullLoadMoreCompleted();
            }
        }

        mAdapter.notifyDataSetChanged();


    }

    @Override
    public void getSatinError(RxException exception) {
        mPullLoadRecyclerView.setPullLoadMoreCompleted();
    }

}
