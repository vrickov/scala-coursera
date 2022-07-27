package funsets

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite extends munit.FunSuite:

  import FunSets.*

  val s0 = (x: Int) => x >= 0

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  test("contains is implemented") {
    assert(!contains(s0, -1))
  }

  test("contains is implemented") {
    assert(contains(s0, 1))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets:
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remove the
   * .ignore annotation.
   */
  test("singleton set one contains one") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets:
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
  }

  test("union contains all elements of each set") {
    new TestSets:
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
  }

  trait TestSets2:
    val s4 = (x: Int) => x % 4 == 0
    val s5 = (x: Int) => x % 5 == 0

  test("intersect contains all elements in both sets") {
    new TestSets2:
      val s = union(s4, s5)
      assert(contains(s, 40), "Intersect 1")
      assert(!contains(s, 6), "Intersect 2")
      assert(contains(s, 20), "Intersect 3")
  }

  test("diff contains all elements that are only in one set") {
    new TestSets2:
      val s = diff(s5, s4)
      assert(!contains(s, 20), "Diff 1")
      assert(contains(s, 25), "Diff 2")
  }

  test("filter returns a new set of elements that satisfy a predicate") {
    new TestSets2:
      val s = filter(s5, x => x % 2 == 0)
      assert(contains(s, 10), "Filter 1")
      assert(!contains(s, 15), "Filter 2")
  }

  test("forall returns whether all elements of a set satisfy a predicate.") {
    new TestSets2:
      assert(forall(s4, x => x % 2 == 0), "Forall 1")
      assert(!forall(s5, x => x % 2 == 0), "Forall 2")
  }

  test("exist returns whether if an element of a set satisfy a predicate") {
    new TestSets2:
      assert(!exists(s5, x => x == 4), "Exist 1")
      assert(exists(s5, x => (x + 3) % 2 == 0), "Exist 2")
  }

  test("map returns a new set transformed by applying a function to each element of a set.") {
    new TestSets2:
      assert(contains(map(s5, x => x / 5), 3), "Map 1")
      assert(!contains(map(s5, x => x + 3), 10), "Map 2")
  }

  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
