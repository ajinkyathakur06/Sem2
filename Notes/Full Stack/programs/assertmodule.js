//assert module for testing
const myas=require('assert')
function add(a, b)
{
    return a+b
}
var res=add(1,2)
myas(res==3,"ok")
myas.notEqual(res,4,"one plus two is three")
var list1=[1,2,3,4]
var list2=[1,2,3,4]
myas.deepEqual(list1,list2,"ok")
var person={'name':'ashwini','eid':100}
var person1={'name':'ashwini','eid':101}
myas.deepEqual(person,person1,"ok")