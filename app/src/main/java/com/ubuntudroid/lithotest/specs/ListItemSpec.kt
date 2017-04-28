package com.ubuntudroid.lithotest.specs

import android.graphics.Color
import com.facebook.litho.Column
import com.facebook.litho.ComponentContext
import com.facebook.litho.ComponentLayout
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaEdge

@LayoutSpec
class ListItemSpec {

    companion object {

        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(context: ComponentContext): ComponentLayout = Column.create(context)
                .paddingDip(YogaEdge.ALL, 16)
                .backgroundColor(Color.WHITE)
                .child(
                        Text.create(context)
                                .text("Hello world")
                                .textSizeSp(40f)
                )
                .child(
                        Text.create(context)
                                .text("Litho tutorial")
                                .textSizeSp(20f)
                )
                .build()
    }
}