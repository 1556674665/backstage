/**
 * Created by Administrator on 2016/8/4.
 */
var zTree, rMenu = $("#rMenu"), rUI = $("#rMenu ul"),roleFlag = true, nodes,ROLEID;
// 图片id
var picture_id;
function Roleload() {
    change();
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
                checkbox: "true",
                field: 'check',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '图片名称',
                field: 'picture_name',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '图片路径',
                field: 'picture_url',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '类型',
                field: 'type',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '创建时间',
                field: 'create_time',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '修改时间',
                field: 'modify_time',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '操作',
                field: '',
                align: 'center',
                formatter: function (value, row) {
                    // var e = '<button button="#" mce_href="#" onclick="editRole(\'' + row.id + '\')">修改</button> '
                    var e = '<button button="#" mce_href="#" onclick="openPicture_modify(\'' + row.id + '\')">修改</button> '
                    var d = '<button button="#" mce_href="#" onclick="delRole(\'' + row.id + '\')">删除</button> '
                    return e +d ;
                }
            }
        ]
    });
    getRoleTableData();
}
function getRoleTableData() {
    var p_name = $("#p_name").val();
    var p_url = $("#p_url").val();
    var p_type = $("#p_type").val();
    var inputdate_start = $("#inputdate_start").val();
    var inputdate_end = $("#inputdate_end").val();

    $.ajax({
        type: "GET",
        url: "../PictureManage/SearchPermission",
        dataType: "json",
        data: {
            "p_name": p_name,
            "p_url": p_url,
            "p_type": p_type,
            "inputdate_start": inputdate_start,
            "inputdate_end": inputdate_end,
        },
        success: function (result) {
            // console.log(result)
            if (result.data) {
                var RoleTableData = result.data;
                $('#table').bootstrapTable("load", RoleTableData);
            }
        }
    })

    $.ajax({
        type: "get",
        url: "../PictureManage/PictureType",
        success: function (result) {
            var data = result.data;
            console.log(data)
            if (data){
                for (let i = 0; i < data.size; i++){
                    var dataI = data[i];
                    var optionHtml = "<option id="+ dataI.id +">"+ dataI.type_name +"</option>"
                    $("#p_type").append(optionHtml);
                }
            }
        },
        error: function () {
            console.log("请求失败");
        }
    })
}

//权限修改
function editRole(id) {
    alert("要修改的图片id：" + id);
    picture_id = id;
      $.ajax({
        type: "GET",
        url: "../RoleManage/SearchPermissionById?id=" + id,
        dataType: "json",
        success: function (result) {
            if (result.data) {
                var RoleData = result.data;
                ROLEID = RoleData.ID;
                $("#ROLE").val(RoleData.NAME);
                $("#ROLENAME").val(RoleData.DESCRIBE);
                var str = RoleData.VALUE;
                var c = str.split(",");
                Tree(c);
        }
        }
    }) 
}

//删除角色
function delRole(id) {
    $.ajax({
        type: "GET",
        url: "../RoleManage/DeletePermission?id=" + id,
        dataType: "json",
        success: function (result) {
            if (result.data) {
                getRoleTableData()
                alert("删除成功");
            }

        }
    })
}

//DOM树初始化setting设置
var setting = {
    check: {
        enable: true
    },
    data: {
        key: { name: "MODULENAME" },
        simpleData: {
            enable: true,
            idKey: "MODULEID",
            pIdKey: "pIdKey",
            rootPId: null
        }
    },
    callback: {
        onCheck: onCheck
    }
};

//加载DOM树数据方法
function Tree(c) {
    var number = c;
    $.ajax({
        type: "POST",
        url: '../RoleManage/GetFunctionModuleList',
        dataType: "json",
        success: function (result) {
            if (result) {
                var nodes = result.data;
                for (var i = 0; i < number.length; i++) {
                    nodes[number[i]].checked = true
                }
                var item = { "MODULENAME": "权限目录", "pIdKey": "-1",checked:true};
                    nodes.push(item);
                $.fn.zTree.init($("#treeDemo"), setting, nodes);
                zTree = $.fn.zTree.getZTreeObj("treeDemo");
                zTree.expandAll(true);
            }
        }
    });
}
function onCheck(e, treeId, treeNode) {
    nodes = zTree.getCheckedNodes()
};

//保存
function onClick() {
    var ROLE = $("#ROLE").val();
    var ROLENAMES = $("#ROLENAME").val()
var v = "";
for (var i = 1; i < nodes.length; i++) {
    if (i == nodes.length - 1) {
        v += nodes[i].MODULEID;
    } else {
        v += nodes[i].MODULEID + ",";
    }
}
if (ROLE.length >0) {
    $.ajax({
        type: "POST",
        url: '../RoleManage/UpdatePermission',
        dataType: "json",
        data: { ID: ROLEID, NAME: ROLE, VALUE: v, DESCRIBE: ROLENAMES },
        success: function (result) {
            if (result) {
                alert("修改成功")
                getRoleTableData();
                $("#ROLE").val("");
                $("#ROLENAME").val("");
                checkCancel();
            }
        }
    });
} else {
    alert(权限修改失败)
}
}

//取消
function checkCancel() {
    $("#ROLE").val("");
    $("#ROLENAME").val("");
    zTree.checkAllNodes(false);
}

function openlayer() {
    layer.open({
        type: 2,
        title: '添加图片',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn: 2,
        area: ['80%', '90%'],
        shadeClose: true,
        closeBtn: 2,
        content: 'picture_create.html'

    });
    
}

function openPicture_modify(id) {
    // alert("要修改的图片id：" + id);
    picture_id = id;
    layer.open({
        type: 2,
        title: '修改图片',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn: 2,
        area: ['80%', '90%'],
        shadeClose: true,
        closeBtn: 2,
        content: 'picture_modify.html'

    });
}



