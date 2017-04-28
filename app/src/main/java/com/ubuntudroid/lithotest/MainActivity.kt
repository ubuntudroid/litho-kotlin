package com.ubuntudroid.lithotest

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.OrientationHelper
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.ComponentInfo
import com.facebook.litho.LithoView
import com.facebook.litho.widget.LinearLayoutInfo
import com.facebook.litho.widget.Recycler
import com.facebook.litho.widget.RecyclerBinder
import com.github.salomonbrys.kodein.android.KodeinAppCompatActivity
import com.github.salomonbrys.kodein.instance
import com.ubuntudroid.lithotest.network.GitHubService
import com.ubuntudroid.lithotest.network.Repo
import com.ubuntudroid.lithotest.specs.ListItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class MainActivity : KodeinAppCompatActivity() {

    private val githubService: GitHubService by instance()
    private val subscriptions = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val componentContext = ComponentContext(this)
        val recyclerBinder = RecyclerBinder(
                componentContext,
                LinearLayoutInfo(this, OrientationHelper.VERTICAL, false))
        val component = Recycler.create(componentContext).binder(recyclerBinder).build()

        addContent(recyclerBinder, componentContext)

        setContentView(LithoView.create(this,component))
    }

    override fun onDestroy() {
        super.onDestroy()

        subscriptions.clear()
    }

    private fun addContent(recyclerBinder: RecyclerBinder, context: ComponentContext) {
        subscriptions.add(
                githubService.listRepos("ubuntudroid")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            recyclerBinder.insertRangeAt(
                                    0,
                                    it.mapIndexed { index, repo ->
                                        ComponentInfo.create()
                                            .component(getListItem(context, repo, index))
                                            .build() }
                            )
                        }
        )
    }

    private fun getListItem(context: ComponentContext, repo: Repo, index: Int): Component<ListItem>? {
        return ListItem.create(context)
                .color(if (index % 2 == 0) Color.WHITE else Color.LTGRAY)
                .title(repo.name)
                .subTitle(repo.description)
                .titleTextSizeSp(Random().nextInt(repo.stargazers_count + 1) + 1f)
                .build()
    }
}
