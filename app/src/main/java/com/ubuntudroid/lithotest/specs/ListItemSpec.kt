package com.ubuntudroid.lithotest.specs

import android.text.TextUtils
import com.facebook.litho.Column
import com.facebook.litho.ComponentContext
import com.facebook.litho.ComponentLayout
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.annotations.ResType
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaEdge

@LayoutSpec
class ListItemSpec {

    companion object {

        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(
                context: ComponentContext,
                @Prop color: Int,
                @Prop title: String,
                @Prop(optional = true) subTitle: String?,
                @Prop(optional = true, resType = ResType.DIMEN_TEXT) titleTextSize: Float): ComponentLayout = Column.create(context)
                .paddingDip(YogaEdge.ALL, 16)
                .backgroundColor(color)
                .child(
                        Text.create(context)
                                .text(title)
                                .textSizeSp(titleTextSize)
                )
                .child(
                        Text.create(context)
                                .text(subTitle)
                                .textSizeSp(20f)
                                .ellipsize(TextUtils.TruncateAt.END)
                )
                .build()
    }
}