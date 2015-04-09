# ListViewHelper
ListViewHelper. 实现下拉刷新，滚动底部自动加载更多，分页加载，自动切换显示网络失败布局，暂无数据布局，,真正的MVC架构.

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

   // 释放资源
		listViewHelper.destory();
		
License
=======

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
