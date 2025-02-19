var assert=require('assert')
assert.throws(
    {
        function(){
            throw new error("my error message")
        } 
    }, Error
)
function add(a, b)
{
    return a+b
}
var res=add(1, 2)
assert(res==3, "one plus two is three")
assert.ok(res==3, "one plus two is three")
assert.equal(res, 3, "one plus two is three")
assert.notEqual(res, 4, "one plus two is not four")

var list1=[1,2,3,4]
var list2=[1,2,3,4]
assert.deepEqual(list1, list2, "both lists are equal")
var person1={'name':'ashwini', 'age':40}
var person2={'name':'ashwini', 'age':40}
assert.deepEqual(person1, person2, "both objects are equal")

var person1={'name':'ashwini', 'age':40}
var person2={'name':'ashwini', 'age':42}
assert.DeepEqual(person1, person2, "both objects are not equal")
