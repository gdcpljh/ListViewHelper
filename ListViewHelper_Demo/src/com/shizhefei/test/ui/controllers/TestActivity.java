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
package com.shizhefei.test.ui.controllers;

import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shizhefei.test.biz.BooksDataSource;
import com.shizhefei.test.entries.Book;
import com.shizhefei.test.ui.adapter.BooksAdapter;
import com.shizhefei.view.listviewhelper.ListViewHelper;
import com.shizhefei.view.listviewhelper.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class TestActivity extends Activity {
	private ListViewHelper<List<Book>> listViewHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		// 设置LoadView的factory，用于创建使用者自定义的加载失败，加载中，加载更多等布局,写法参照DeFaultLoadViewFactory
		// ListViewHelper.setLoadViewFractory(new LoadViewFractory());

		PullToRefreshListView refreshListView = (PullToRefreshListView) findViewById(R.id.pullToRefreshListView);
		listViewHelper = new ListViewHelper<List<Book>>(refreshListView);

		// 设置数据源
		listViewHelper.setDataSource(new BooksDataSource());
		// 设置适配器
		listViewHelper.setAdapter(new BooksAdapter(this));

		// 加载数据
		listViewHelper.refresh();
	}

	public void onClickBack(View view) {
		finish();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 释放资源
		listViewHelper.destory();
	}
}
