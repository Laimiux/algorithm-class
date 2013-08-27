

object Week2 {
  def mapReduce(f: Int => Int, combine: (Int, Int) => Int,baseValue: Int)(a: Int, b: Int) = {
    def inner_loop(a: Int, acc: Int): Int =
      if (a > b) acc
      else inner_loop(a + 1, combine(acc,f(a)))
    inner_loop(a, baseValue)
  }                                               //> mapReduce: (f: Int => Int, combine: (Int, Int) => Int, baseValue: Int)(a: In
                                                  //| t, b: Int)Int

  def product(f: Int => Int)(a: Int, b: Int): Int =
  	mapReduce(f, (x, y) => x * y, 1)(a,b)     //> product: (f: Int => Int)(a: Int, b: Int)Int

  product(x => x)(1, 10)                          //> res0: Int = 3628800

  def factorial(n: Int) = product(x => x)(1, n)   //> factorial: (n: Int)Int

}