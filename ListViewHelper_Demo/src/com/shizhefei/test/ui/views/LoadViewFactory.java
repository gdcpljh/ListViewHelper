package com.shizhefei.test.ui.views;

import com.shizhefei.view.listviewhelper.ILoadViewFactory;

/**
 * 使用者可以创建自定义布局,写法可以参照com.shizhefei.view.listviewhelper.imp.
 * DeFaultLoadViewFactory
 * 
 * @author LuckyJayce
 *
 */
public class LoadViewFactory implements ILoadViewFactory {

	@Override
	public ILoadMoreView madeLoadMoreView() {

		return null;
	}

	@Override
	public ILoadView madeLoadView() {

		return null;
	}

}
