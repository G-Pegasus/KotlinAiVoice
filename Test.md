调用RecyclerView
mList = ArrayList()

        // 模拟数据
        for (i in 1..30) {
            mList.add("第${i}个")
        }

        binding.mRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mRecyclerView.adapter = CommonAdapter(mList, object : CommonAdapter.OnBindDataListener<String> {
            override fun onBindViewHolder(
                model: String, viewHolder: CommonViewHolder, type: Int, position: Int
            ) {
                viewHolder.setText(R.id.textView, model)
            }

            override fun getLayoutId(type: Int): Int {
                return R.layout.test_item
            }
        })