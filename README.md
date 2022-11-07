# XNestedRecyclerView

## 1）项目介绍
本库是一个处理RecyclerView+ViewPager+RecyclerView的嵌套滑动的库，外层和里面的RecyclerView都是上下滑动，ViewPager是横向滑动。

基于NestedScrolling机制实现，考虑各种场景，已上线使用，功能稳定，具体用例有：

- 外层RecyclerView滑到底，不松手，可继续对里面的RecyclerView进行滑动
- 里面的RecyclerView滑到顶时，不松手，可继续滑动外层的RecyclerView
- 支持fling穿透。从外到内，外层fling没结束时，里面的RecyclerView可继续滑动。从内到外，里面的fling没结束时，外层的RecyclerView继续滑动
- 支持滑动穿透后，不松手继续fling的场景
- ViewPager横滑不影响
- 分页加载不影响，外层和里面的RecyclerView都可异步分页加载
- 外层可继续嵌套下拉刷新组件

## 2）演示效果
![穿透滑动](https://m.360buyimg.com/img/jfs/t1/173247/24/30191/736387/6329708eEae93c38f/79499d0353fecc64.gif)![fling穿透](https://m.360buyimg.com/img/jfs/t1/199554/18/27254/957617/6329707cE7d9377fa/6dd57e97e9609ffd.gif)

## 3）使用方式（详情见demo）

1. 布局，同普通自定义view

```java
<com.jd.lib.xnestedrecyclerview.ParentRecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:overScrollMode="never" />
```

2. 初始化

```java
//嵌套滑动开关
((ParentRecyclerView) recyclerView).setAllowNestScroll(true);
//设置LayoutManager，需使用NestLinearLayoutManager或NestGridLayoutManager或NestStaggeredGridLayoutManager
recyclerView.setLayoutManager(new NestLinearLayoutManager(context));
//adapter无特殊要求
recyclerView.setAdapter(adapter);
```

3. 内层view（ViewPager+RecyclerView部分）

```java
//view需要继承NestViewPagerView，tab切换可随意增加，部分细节点详见demo
//viewPager的adapter需继承NestViewPagerAdapter
//内层的RecyclerView需要使用NestRecyclerView
recyclerView.setAdapter(adapter);
```

## 4）其他
目前该组件是稳定版，使用NestedScrollingParent3可优化fling部分的代码，但实际测试发现，从里面的RecyclerView fling到外层的RecyclerView时，会出现卡在一屏的效果，即fling只能展示一屏外层的RecyclerView，会出现fling中断的现象。具体实现暂时没有上传，有解决办法也欢迎共建。

## 5）License
```
Copyright 2022 JD, Inc. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```