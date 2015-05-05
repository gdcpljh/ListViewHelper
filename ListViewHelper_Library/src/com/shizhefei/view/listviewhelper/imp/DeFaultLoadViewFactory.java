/*
Copyright 2015 shizhefei（LuckyJayce）

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.shizhefei.view.listviewhelper.imp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shizhefei.view.listviewhelper.ILoadViewFactory;
import com.shizhefei.view.listviewhelper.R;
import com.shizhefei.view.vary.VaryViewHelper;

public class DeFaultLoadViewFactory implements ILoadViewFactory {

	@Override
	public ILoadMoreView madeLoadMoreView() {
		return new LoadMoreHelper();
	}

	@Override
	public ILoadView madeLoadView() {
		return new LoadViewHelper();
	}

	private class LoadMoreHelper implements ILoadMoreView {

		protected TextView footView;

		@Override
		public void init(ListView listView, OnClickListener onClickRefreshListener) {
			footView = (TextView) LayoutInflater.from(listView.getContext()).inflate(R.layout.layout_listview_foot, listView, false);
			footView.setOnClickListener(onClickRefreshListener);
			listView.addFooterView(footView);
			showNormal();
		}

		@Override
		public void showNormal() {
			footView.setText("点击加载更多");
		}

		@Override
		public void showLoading() {
			footView.setText("正在加载中..");
		}

		@Override
		public void showFail() {
			footView.setText("加载失败，点击重新加载");
		}

		@Override
		public void showNomore() {
			footView.setText("已经加载完毕");
			footView.setOnClickListener(null);
		}

	}

	private class LoadViewHelper implements ILoadView {
		private VaryViewHelper helper;
		private OnClickListener onClickRefreshListener;
		private Context context;

		@Override
		public void init(ListView mListView, OnClickListener onClickRefreshListener) {
			helper = new VaryViewHelper(mListView);
			this.context = mListView.getContext().getApplicationContext();
			this.onClickRefreshListener = onClickRefreshListener;
		}

		@Override
		public void restore() {
			helper.restoreView();
		}

		@Override
		public void showLoading() {
			View layout = helper.inflate(R.layout.load_ing);
			TextView textView = (TextView) layout.findViewById(R.id.textView1);
			textView.setText("加载中...");
			helper.showLayout(layout);
		}

		@Override
		public void tipFail() {
			Toast.makeText(context, "网络加载失败", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void showFail() {
			View layout = helper.inflate(R.layout.load_error);
			TextView textView = (TextView) layout.findViewById(R.id.textView1);
			textView.setText("网络加载失败");
			Button button = (Button) layout.findViewById(R.id.button1);
			button.setText("重试");
			button.setOnClickListener(onClickRefreshListener);
			helper.showLayout(layout);
		}

		@Override
		public void showEmpty() {
			View layout = helper.inflate(R.layout.load_empty);
			TextView textView = (TextView) layout.findViewById(R.id.textView1);
			textView.setText("暂无数据");
			Button button = (Button) layout.findViewById(R.id.button1);
			button.setText("重试");
			button.setOnClickListener(onClickRefreshListener);
			helper.showLayout(layout);
		}

	}
}
