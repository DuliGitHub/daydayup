###day01 :
 - Redis 有 5 种基础数据结构，分别为：string (字符串)、list (列表)、set (集合)、hash (哈希) 和 zset (有序集合)。
 - 批量键值对 :可以批量对多个字符串进行读写，节省网络耗时开销
    - mget name1 name2 name3
    - mset name1 name2 name3
 - 过期
    - set name dul    **expire** name 5  # 5s 后过期
    - **setex** name 5 dul  # 5s 后过期，等价于 set+expire
 - **setnx** name dul  # 如果 name 不存在就执行 set 创建
 - 自增
    - **incr + key  :key+1**
    - **incrby + key  + num :key增加num**,范围是 signed long 的最大最小值
 - list (列表) : Redis 的列表结构常用来做**异步队列**使用。将需要延后处理的任务结构体序列化成字符串塞进 Redis 的列表，另一个线程从这个列表中轮询数据进行处理。
   > - rpush、lpush、rpop、lpop
   > - rpush books python java golang、
   > - lrange books 0 -1  # 获取所有元素，O(n) 慎用
   > - ltrim books 1 -1 # O(n) 慎用 删除第一个
   > - ltrim books 1 0 # 这其实是清空了整个列表，因为区间范围长度为负
 - Redis 底层存储的还不是一个简单的 linkedlist，而是称之为快速链表 quicklist 的一个结构
 -首先在列表元素较少的情况下会使用一块连续的内存存储，这个结构是 ziplist，也即是压缩列表。
 它将所有的元素紧挨着一起存储，**分配的是一块连续的内存**。当数据量比较多的时候才会改成 quicklist。
 因为普通的链表需要的附加指针空间太大，会比较浪费空间，而且会加重内存的碎片化。
 比如这个列表里存的只是 int 类型的数据，结构上还需要两个额外的指针 prev 和 next 。所以 Redis 将链表和 ziplist 结合起来组成了 quicklist。也就是将多个 ziplist 使用双向指针串起来使用。这样既满足了快速的插入删除性能，又不会出现太大的空间冗余。
 - hash (字典)
    - hset books java "think in java"  # 命令行的字符串如果包含空格，要用引号括起来
    - hset books golang "concurrency in go"
    - hgetall books  # entries()，key 和 value 间隔出现
    - hlen books
    - hget books java
    -  hmset books java "effective java" python "learning python" golang "modern golang programming"  # 批量 set
 - set (集合) : Redis 的集合相当于 Java 语言里面的 HashSet，它内部的键值对是无序的唯一的。它的内部实现相当于一个特殊的字典，字典中所有的 value 都是一个值NULL
    - sadd books python
    - sadd books python  #  重复
    - smembers books  # 注意顺序，和插入的并不一致，因为 set 是无序的
    -  sismember books java  # 查询某个 value 是否存在，相当于 contains(o)
    - scard books  # 获取长度相当于 count()
    - spop books  # 弹出一个
 - zset (有序集合) :它类似于 Java 的 SortedSet 和 HashMap 的结合体，一方面它是一个 set，保证了内部 value 的唯一性，另一方面它可以给每个 value 赋予一个 score，代表这个 value 的排序权重。它的内部实现用的是一种叫做「跳跃列表」的数据结构
    - zadd books 9.0 "think in java"
    - zadd books 8.9 "java concurrency"
    - zadd books 8.6 "java cookbook"
    -  zrange books 0 -1  # 按 score 排序列出，参数区间为排名范围
    - zrevrange books 0 -1  # 按 score 逆序列出，参数区间为排名范围
    - zcard books  # 相当于 count()
    - zscore books "java concurrency"  # 获取指定 value 的 score
    -  zrank books "java concurrency"  # 排名
    - zrangebyscore books 0 8.91  # 根据分值区间遍历 zset
    - zrangebyscore books -inf 8.91 withscores # 根据分值区间 (-∞, 8.91] 遍历 zset，同时返回分值。inf 代表 infinite，无穷大的意思。
    - zrem books "java concurrency"  # 删除 value
    
  