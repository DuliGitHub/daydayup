#### java8 内置的四大核心函数式接口
- Consumer<T>: 消费型接口
  - void accept(T t)
- Supplier<T>: 供给型接口 
    - T get();
- Function<T,R>: 函数型接口 
    - R apply(T t);
- Predicate<T>: 断言型接口 
    - boolean test(T t);