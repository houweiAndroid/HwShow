package com.example.houwei.hwshop.ptrm;

import android.view.View;

public interface PtrHandler {

    /**
     * Check can do refresh or not. For example the content is empty or the first child is in view.
     *
     * {@link com.example.houwei.hwshop.ptrm.PtrDefaultHandler#checkContentCanBePulledDown}
     */
    abstract boolean checkCanDoRefresh(final PtrFrameLayout frame, final View content, final View header);

    /**
     * When refresh begin
     *
     * @param frame
     */
    public abstract void onRefreshBegin(final PtrFrameLayout frame);
}