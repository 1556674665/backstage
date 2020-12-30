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
                title: '学校名称',
                field: 'school_name',
                align: 'center',
                formatter:function (value,row) {
                    return row["school_name"]
                }
            },{
                title: '商务合作',
                field: 'business_cooperation',
                align: 'center',
                formatter:function (value,row) {
                    return row["business_cooperation"]
                }
            },{
                title: '电子邮件',
                field: 'e_mail',
                align: 'center',
                formatter:function (value,row) {
                    return row["e_mail"]
                }
            },
            {
                title: "报名电话",
                field: 'registration_telephone',
                align: 'center',
                valign: 'middle',
                formatter:function (value,row) {
                    return row["registration_telephone"];
                }
            },
            {
                title: "学校地址",
                field: 'school_address',
                align: 'center',
                valign: 'middle',
                formatter:function (value,row) {
                        return row["school_address"]
                }
            },
            {
                title: '学院网址',
                field: 'school_website',
                align: 'center',
                valign: 'middle',
                formatter:function (value,row) {
                        return row["school_website"]
                }
            },
            {
                title: '附近公交站台',
                field: 'nearby_bus_stop',
                align: 'center',
                formatter:function (value,row) {
                    return row["nearby_bus_stop"]
                }
            },
            {
                title: '附近公交车',
                field: 'nearby_bus',
                align: 'center',
                formatter:function (value,row) {
                    return row["nearby_bus"]
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
        url: "/selectRoute_to_school_table",
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
        url:"/delRoute_to_school_table",
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
        content: '/selectRoute_to_school_table_s?id='+id
        //iframe的url
    });
}





