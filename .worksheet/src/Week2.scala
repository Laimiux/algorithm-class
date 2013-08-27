

object Week2 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(256); 
  def mapReduce(f: Int => Int, combine: (Int, Int) => Int,baseValue: Int)(a: Int, b: Int) = {
    def inner_loop(a: Int, acc: Int): Int =
      if (a > b) acc
      else inner_loop(a + 1, combine(acc,f(a)))
    inner_loop(a, baseValue)
  };System.out.println("""mapReduce: (f: Int => Int, combine: (Int, Int) => Int, baseValue: Int)(a: Int, b: Int)Int""");$skip(94); 

  def product(f: Int => Int)(a: Int, b: Int): Int =
  	mapReduce(f, (x, y) => x * y, 1)(a,b);System.out.println("""product: (f: Int => Int)(a: Int, b: Int)Int""");$skip(26); val res$0 = 

  product(x => x)(1, 10);System.out.println("""res0: Int = """ + $show(res$0));$skip(49); 

  def factorial(n: Int) = product(x => x)(1, n);System.out.println("""factorial: (n: Int)Int""")}

}
