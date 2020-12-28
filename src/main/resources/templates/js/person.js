/**
 * Created by Administrator on 2016/8/4.
 */

var user, role, currentID, flag = true;
function Personload() {
    $('#table').bootstrapTable({
        method: "get",
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
                title: "课程名称",
                field: 'curriculum_name',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '创建时间',
                field: 'create_date',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '修改时间',
                field: 'modify_date',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '操作',
                field: '',
                align: 'center',
                formatter: function (value, row) {
                    var e = '<button button="#" mce_href="#" onclick="delNotice(\'' + row.id + '\')">删除</button> '
                    var d = '<button button="#" mce_href="#" onclick="editNotice(\'' + row.id + '\')">编辑</button> ';
                    return e + d;
                }
            }
        ]
    });
    getData();
}
function getData() {
    var curriculum_name = $("#curriculum_name").val();
    $.ajax({
        type: "GET",
        url: "../CurriculumManage/SearchPermission",
        dataType: "json",
        data: {"curriculum_name": curriculum_name},
        success: function (result) {
            if (result.data) {
                var TableData = result.data;
                $('#table').bootstrapTable("load", TableData);
            }
        }
    })
}
function add() {
    openlayer()
}
function edit(id) {
    openlayer()
    currentID = id;
}
// 课程id（通过选择的课程的id查询课程数据做修改操作）
var curriculum_id;
function editNotice(id) {
    // alert(id)
    curriculum_id = id;
    openCurriculumModify()
}
function del(id) {
    alert(id)
    var NoticeId = id;
    $.ajax({
        url: '../WorkRecord/DeleteWork?workId=' + NoticeId,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            if (data.data) {
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
function openlayer(){
    layer.open({
        type: 2,
        title: '添加课程',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
//            maxmin: true,
        closeBtn:2,
        area: ['80%', '90%'],
        shadeClose: true,
        closeBtn: 2,
        content: 'curriculum_tail.html'
        // content: 'picture.html'
        //iframe的url
    });

}

function openCurriculumModify() {
    layer.open({
        type: 2,
        title: '通知信息',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn: 2,
        area: ['98%', '98%'],
        shadeClose: true,
        closeBtn: 2,
        content: 'curriculum_modify.html'

    });

}





