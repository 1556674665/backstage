/**
 * Created by Administrator on 2016/8/4.
 */

var user, role, currentID, flag = true;
function Personload() {
    $('#table').bootstrapTable({
        method: "POST",
        striped: true,
        singleSelect: false,
        dataType: "json",
        pagination: true, //分页
        pageSize: 10,
        pageNumber: 1,
        search: false, //显示搜索框
        contentType: "application/x-www-form-urlencoded",
        queryParams: null,
        columns: [
            {
                checkbox:"true",
                field: 'ID',
                align: 'center',
                valign: 'middle'
            },
            {
                title: "标题",
                field: 'title',
                align: 'center',
                valign: 'middle',
                formatter:function (value,row) {
                    return row["title"];
                }
            },
            {
                title: '来源',
                field: 'source',
                align: 'center',
                valign: 'middle',
                formatter:function (value,row) {
                    return row["source"];
                }
            },
            {
                title: '内容',
                field: 'content',
                align: 'center',
                formatter:function (value,row) {
                    if (row["content"].length > 10){
                        return row["content"].substring(0,9) + "......"
                    }else {
                        return row["content"]
                    }
                }
            },
            {
                title: '创建时间',
                field: 'create_date',
                align: 'center',
                valign: 'middle',
                formatter: function (value,row) {
                    return row["create_date"];
                }
            },
            {
                title: '修改时间',
                field: 'modify_date',
                align: 'center',
                valign: 'middle',
                formatter: function (value,row) {
                    return row["modify_date"];
                }
            },
            {
                title: '操作',
                field: '',
                align: 'center',
                formatter: function (value, row) {
                    var e = '<button button="#" mce_href="#" onclick="del(\'' + row["id"] + '\')">删除</button> '
                    var d = '<button button="#" mce_href="#" onclick="edit(\'' + row["id"] + '\')">编辑</button> ';
                    return e + d;
                }
            }
        ]
    });
    getData();
}
function getData() {
    if (flag) {
        user = "";

        flag = false;
    } else {
        user = $("#user").val();

    }
    $.ajax({
        type: "POST",
        url: "/selectCollege_News_table",
        dataType: "json",
        data: {user:user},
        success: function (result) {
            console.log(result)
            if (result) {
                var TableData = result;
                $('#table').bootstrapTable("load", TableData);
            }
        }
    })
}
function add() {
    openlayer()
    currentID = "";
}
function edit(id) {
    openlayer(id)
    currentID = id;
}
function del(id) {
    var NoticeId = id;
    $.ajax({
        url:"/delCollege_News_table",
        type: 'POST',
        dataType: 'json',
        data:{id:NoticeId},
        success: function (data) {
            if (data.data==1) {
                alert("删除成功！")
                getData();
            } else {
                alert("删除失败")
            }
        },
        error: function (err) {
        }
    });
}
function getCurrentID() {
    return currentID;
}
function openlayer(id){
    layer.open({
        type: 2,
        title: '编辑信息',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
//            maxmin: true,
        closeBtn:2,
        area: ['80%', '90%'],
        shadeClose: true,
        closeBtn: 2,
        content: '/selectCollege_News_table_s?id='+id
        //iframe的url
    });
}





