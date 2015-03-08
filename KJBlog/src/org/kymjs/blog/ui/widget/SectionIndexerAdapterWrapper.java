package org.kymjs.blog.ui.widget;

import android.content.Context;
import android.widget.SectionIndexer;

/**
 * 对Adapter封装
 * 
 * @author kymjs (https://github.com/kymjs)
 * @author Jake Wharton (jakewharton@gmail.com)
 */
class SectionIndexerAdapterWrapper extends AdapterWrapper implements
        SectionIndexer {

    final SectionIndexer mSectionIndexerDelegate;

    SectionIndexerAdapterWrapper(Context context, StickyHeadAdapter delegate) {
        super(context, delegate);
        mSectionIndexerDelegate = (SectionIndexer) delegate;
    }

    @Override
    public int getPositionForSection(int section) {
        return mSectionIndexerDelegate.getPositionForSection(section);
    }

    @Override
    public int getSectionForPosition(int position) {
        return mSectionIndexerDelegate.getSectionForPosition(position);
    }

    @Override
    public Object[] getSections() {
        return mSectionIndexerDelegate.getSections();
    }
}
