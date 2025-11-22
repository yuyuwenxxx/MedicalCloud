<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.bootcdn.net/ajax/libs/normalize/8.0.1/normalize.min.css" rel="stylesheet">
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.4.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>医疗云系统 - 患者管理</title>
    <style>
    
        body {
            font-family: 'Microsoft YaHei', Arial, sans-serif;
            background-color: #f8f9fa;
            line-height: 1.8;
        }
        
        .header {
            background: linear-gradient(135deg, #1e66d9, #0c57c2);
            color: white;
            padding: 20px 0;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        
        .navbar {
            background-color: #0d5fc9 !important;
            border: none;
            border-radius: 0;
            margin-bottom: 0;
        }
        
        .navbar-default .navbar-brand,
        .navbar-default .navbar-nav > li > a {
            color: white !important;
            padding: 15px 20px;
            transition: all 0.3s ease;
        }
        
        .navbar-default .navbar-nav > li > a:hover {
            background-color: #0a4eaa !important;
        }
        
        .navbar-default .navbar-nav > .active > a {
            background-color: #0a4eaa !important;
            box-shadow: inset 0 -3px 0 #fff;
        }
        
        .page-title {
            background-color: #e3f2fd;
            padding: 25px 0;
            margin-bottom: 30px;
            border-bottom: 1px solid #d1e3f8;
        }
        
        .page-title h2 {
            color: #0d5fc9;
            margin: 0;
            font-weight: 600;
        }
        
        .stats-panel {
            margin-bottom: 30px;
        }
        
        .stat-card {
            background: white;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.08);
            text-align: center;
            transition: all 0.3s ease;
        }
        
        .stat-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 20px rgba(0,0,0,0.15);
        }
        
        .stat-number {
            font-size: 2.5em;
            font-weight: bold;
            color: #0d5fc9;
        }
        
        .stat-label {
            color: #666;
            font-size: 1.1em;
            margin-top: 5px;
        }
        
        .search-panel {
            background: white;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 25px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.08);
        }
        
        .patient-table-container {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.08);
            padding: 20px;
        }
        
        .table-title {
            color: #0d5fc9;
            font-size: 18px;
            font-weight: 600;
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 2px solid #e3f2fd;
        }
        
        .btn-primary {
            background: linear-gradient(135deg, #0d5fc9, #0a4eaa);
            border: none;
            border-radius: 6px;
            padding: 8px 20px;
            font-weight: 500;
            transition: all 0.3s ease;
        }
        
        .btn-primary:hover {
            background: linear-gradient(135deg, #0a4eaa, #084191);
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(13, 95, 201, 0.3);
        }
        
        .btn-success {
            background-color: #28a745;
            border: none;
            border-radius: 6px;
            padding: 8px 20px;
        }
        
        .btn-info {
            background-color: #17a2b8;
            border: none;
            border-radius: 6px;
            padding: 6px 15px;
        }
        
        .btn-warning {
            background-color: #ffc107;
            border: none;
            border-radius: 6px;
            padding: 6px 15px;
            color: #333;
        }
        
        .btn-danger {
            background-color: #dc3545;
            border: none;
            border-radius: 6px;
            padding: 6px 15px;
        }
        
        .status-badge {
            display: inline-block;
            padding: 3px 10px;
            border-radius: 12px;
            font-size: 12px;
            font-weight: 600;
        }
        
        .status-active {
            background-color: #d4edda;
            color: #155724;
        }
        
        .status-inactive {
            background-color: #f8d7da;
            color: #721c24;
        }
        
        .status-pending {
            background-color: #fff3cd;
            color: #856404;
        }
        
        .filter-group {
            display: flex;
            align-items: center;
            gap: 10px;
            margin-bottom: 15px;
        }
        
        .filter-group label {
            min-width: 80px;
            margin: 0;
            font-weight: 500;
        }
        
        .form-control {
            border: 1px solid #ced4da;
            border-radius: 6px;
            padding: 8px 12px;
            transition: all 0.3s ease;
        }
        
        .form-control:focus {
            border-color: #0d5fc9;
            box-shadow: 0 0 0 3px rgba(13, 95, 201, 0.1);
        }
        
        .pagination-wrapper {
            text-align: right;
            margin-top: 20px;
        }
        
        .pagination > li > a {
            color: #0d5fc9;
        }
        
        .pagination > .active > a {
            background-color: #0d5fc9;
            border-color: #0d5fc9;
        }
        
        .action-buttons {
            display: flex;
            gap: 5px;
            justify-content: center;
        }
        
        .quick-actions {
            position: fixed;
            right: 30px;
            bottom: 30px;
            z-index: 1000;
        }
        
        .quick-action-btn {
            width: 60px;
            height: 60px;
            border-radius: 50%;
            background: linear-gradient(135deg, #0d5fc9, #0a4eaa);
            color: white;
            border: none;
            font-size: 24px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.2);
            transition: all 0.3s ease;
        }
        
        .quick-action-btn:hover {
            transform: scale(1.1);
            box-shadow: 0 6px 20px rgba(0,0,0,0.3);
        }
        
        .modal-header {
            background: linear-gradient(135deg, #0d5fc9, #0a4eaa);
            color: white;
        }
        
        .modal-header .close {
            color: white;
            opacity: 0.8;
        }
        
        .timeline-item {
            position: relative;
            padding-left: 40px;
            margin-bottom: 20px;
        }
        
        .timeline-item::before {
            content: '';
            position: absolute;
            left: 15px;
            top: 20px;
            bottom: -20px;
            width: 2px;
            background-color: #dee2e6;
        }
        
        .timeline-item:last-child::before {
            display: none;
        }
        
        .timeline-dot {
            position: absolute;
            left: 10px;
            top: 5px;
            width: 12px;
            height: 12px;
            border-radius: 50%;
            background-color: #0d5fc9;
            border: 2px solid #fff;
            box-shadow: 0 0 0 4px rgba(13, 95, 201, 0.1);
        }
        
        .timeline-content {
            background: #f8f9fa;
            padding: 10px 15px;
            border-radius: 6px;
        }
        
        .timeline-date {
            color: #6c757d;
            font-size: 12px;
        }
    </style>
</head>
<body>
    <%
        // 检查用户是否登录且为医生或管理员
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        com.medical.model.User user = (com.medical.model.User) session.getAttribute("user");
        if (!"DOCTOR".equals(user.getUserType()) && !"ADMIN".equals(user.getUserType())) {
            response.sendRedirect("profile.jsp");
            return;
        }
    %>
    
    <!-- 系统头部 -->
    <div class="header">
        <div class="container">
            <h1>医疗云系统 - 患者管理中心</h1>
            <p>全面管理患者信息与就诊记录</p>
        </div>
    </div>

    <!-- 导航菜单 -->
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
                        data-target="#patient-nav" aria-expanded="false">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="doctor_dashboard.jsp" class="navbar-brand">医生工作站</a>
            </div>
            <div class="collapse navbar-collapse" id="patient-nav">
                <ul class="nav navbar-nav">
                    <li><a href="doctor_dashboard.jsp"><i class="fa fa-dashboard"></i> 工作台</a></li>
                    <li><a href="case_record.jsp"><i class="fa fa-edit"></i> 病例填写</a></li>
                    <li><a href="doctor_process.html"><i class="fa fa-stethoscope"></i> 就诊管理</a></li>
                    <li class="active"><a href="patient_manage.jsp"><i class="fa fa-users"></i> 患者管理</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#" style="color: #ffeb3b !important;">
                        <i class="fa fa-user-md"></i> 医生：${user.realName}
                    </a></li>
                    <li><a href="login?action=logout"><i class="fa fa-sign-out"></i> 退出登录</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- 页面标题 -->
    <div class="page-title">
        <div class="container">
            <h2><i class="fa fa-users"></i> 患者管理</h2>
        </div>
    </div>

    <!-- 主要内容区域 -->
    <div class="container">
        <!-- 统计卡片 -->
        <div class="stats-panel">
            <div class="row">
                <div class="col-md-3">
                    <div class="stat-card">
                        <div class="stat-number">1,256</div>
                        <div class="stat-label">总患者数</div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="stat-card">
                        <div class="stat-number">89</div>
                        <div class="stat-label">今日就诊</div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="stat-card">
                        <div class="stat-number">45</div>
                        <div class="stat-label">待复诊</div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="stat-card">
                        <div class="stat-number">23</div>
                        <div class="stat-label">新增患者</div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 搜索筛选面板 -->
        <div class="search-panel">
            <h4 style="color: #0d5fc9; margin-bottom: 20px;"><i class="fa fa-search"></i> 患者搜索</h4>
            <form id="searchForm">
                <div class="row">
                    <div class="col-md-4">
                        <div class="filter-group">
                            <label>患者ID：</label>
                            <input type="text" class="form-control" name="patientId" placeholder="输入患者ID">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="filter-group">
                            <label>患者姓名：</label>
                            <input type="text" class="form-control" name="patientName" placeholder="输入患者姓名">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="filter-group">
                            <label>手机号：</label>
                            <input type="text" class="form-control" name="phone" placeholder="输入手机号">
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 15px;">
                    <div class="col-md-3">
                        <div class="filter-group">
                            <label>性别：</label>
                            <select class="form-control" name="gender">
                                <option value="">全部</option>
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="filter-group">
                            <label>年龄段：</label>
                            <select class="form-control" name="ageRange">
                                <option value="">全部</option>
                                <option value="0-18">0-18岁</option>
                                <option value="19-35">19-35岁</option>
                                <option value="36-50">36-50岁</option>
                                <option value="51-65">51-65岁</option>
                                <option value="65+">65岁以上</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="filter-group">
                            <label>状态：</label>
                            <select class="form-control" name="status">
                                <option value="">全部</option>
                                <option value="active">活跃</option>
                                <option value="inactive">非活跃</option>
                                <option value="pending">待处理</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="filter-group" style="align-items: flex-end;">
                            <button type="submit" class="btn btn-primary">
                                <i class="fa fa-search"></i> 搜索
                            </button>
                            <button type="button" class="btn btn-default" onclick="resetSearch()">
                                <i class="fa fa-refresh"></i> 重置
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <!-- 患者列表 -->
        <div class="patient-table-container">
            <div class="row">
                <div class="col-md-6">
                    <div class="table-title">患者列表</div>
                </div>
                <div class="col-md-6 text-right">
                    <button class="btn btn-success" onclick="addNewPatient()">
                        <i class="fa fa-plus"></i> 新增患者
                    </button>
                    <button class="btn btn-primary" onclick="exportPatients()">
                        <i class="fa fa-download"></i> 导出数据
                    </button>
                </div>
            </div>
            
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th><input type="checkbox" id="selectAll"></th>
                            <th>患者ID</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>年龄</th>
                            <th>联系电话</th>
                            <th>最后就诊</th>
                            <th>就诊次数</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="checkbox" class="patient-select"></td>
                            <td>P2025001</td>
                            <td>张三</td>
                            <td>男</td>
                            <td>35</td>
                            <td>138****1234</td>
                            <td>2025-01-15</td>
                            <td>5</td>
                            <td><span class="status-badge status-active">活跃</span></td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn btn-info btn-sm" onclick="viewPatient('P2025001')">
                                        <i class="fa fa-eye"></i>
                                    </button>
                                    <button class="btn btn-warning btn-sm" onclick="editPatient('P2025001')">
                                        <i class="fa fa-edit"></i>
                                    </button>
                                    <button class="btn btn-danger btn-sm" onclick="deletePatient('P2025001')">
                                        <i class="fa fa-trash"></i>
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" class="patient-select"></td>
                            <td>P2025002</td>
                            <td>李四</td>
                            <td>女</td>
                            <td>28</td>
                            <td>139****5678</td>
                            <td>2025-01-10</td>
                            <td>3</td>
                            <td><span class="status-badge status-active">活跃</span></td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn btn-info btn-sm" onclick="viewPatient('P2025002')">
                                        <i class="fa fa-eye"></i>
                                    </button>
                                    <button class="btn btn-warning btn-sm" onclick="editPatient('P2025002')">
                                        <i class="fa fa-edit"></i>
                                    </button>
                                    <button class="btn btn-danger btn-sm" onclick="deletePatient('P2025002')">
                                        <i class="fa fa-trash"></i>
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" class="patient-select"></td>
                            <td>P2025003</td>
                            <td>王五</td>
                            <td>男</td>
                            <td>45</td>
                            <td>137****9012</td>
                            <td>2025-01-05</td>
                            <td>8</td>
                            <td><span class="status-badge status-pending">待处理</span></td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn btn-info btn-sm" onclick="viewPatient('P2025003')">
                                        <i class="fa fa-eye"></i>
                                    </button>
                                    <button class="btn btn-warning btn-sm" onclick="editPatient('P2025003')">
                                        <i class="fa fa-edit"></i>
                                    </button>
                                    <button class="btn btn-danger btn-sm" onclick="deletePatient('P2025003')">
                                        <i class="fa fa-trash"></i>
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" class="patient-select"></td>
                            <td>P2025004</td>
                            <td>赵六</td>
                            <td>女</td>
                            <td>52</td>
                            <td>136****3456</td>
                            <td>2024-12-20</td>
                            <td>12</td>
                            <td><span class="status-badge status-inactive">非活跃</span></td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn btn-info btn-sm" onclick="viewPatient('P2025004')">
                                        <i class="fa fa-eye"></i>
                                    </button>
                                    <button class="btn btn-warning btn-sm" onclick="editPatient('P2025004')">
                                        <i class="fa fa-edit"></i>
                                    </button>
                                    <button class="btn btn-danger btn-sm" onclick="deletePatient('P2025004')">
                                        <i class="fa fa-trash"></i>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            
            <!-- 分页 -->
            <div class="pagination-wrapper">
                <nav>
                    <ul class="pagination">
                        <li class="disabled"><a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a></li>
                        <li class="active"><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>

    <!-- 快速操作按钮 -->
    <div class="quick-actions">
        <button class="quick-action-btn" onclick="addNewPatient()">
            <i class="fa fa-plus"></i>
        </button>
    </div>

    <!-- 患者详情模态框 -->
    <div class="modal fade" id="patientDetailModal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title"><i class="fa fa-user"></i> 患者详情</h4>
                </div>
                <div class="modal-body">
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active">
                            <a href="#basicInfo" aria-controls="basicInfo" role="tab" data-toggle="tab">基本信息</a>
                        </li>
                        <li role="presentation">
                            <a href="#medicalHistory" aria-controls="medicalHistory" role="tab" data-toggle="tab">就诊记录</a>
                        </li>
                        <li role="presentation">
                            <a href="#prescriptions" aria-controls="prescriptions" role="tab" data-toggle="tab">处方记录</a>
                        </li>
                        <li role="presentation">
                            <a href="#examResults" aria-controls="examResults" role="tab" data-toggle="tab">检查结果</a>
                        </li>
                    </ul>
                    
                    <div class="tab-content" style="margin-top: 20px;">
                        <!-- 基本信息 -->
                        <div role="tabpanel" class="tab-pane active" id="basicInfo">
                            <div class="row">
                                <div class="col-md-6">
                                    <p><strong>患者ID：</strong> <span id="modalPatientId">P2025001</span></p>
                                    <p><strong>姓名：</strong> <span id="modalPatientName">张三</span></p>
                                    <p><strong>性别：</strong> <span id="modalGender">男</span></p>
                                    <p><strong>年龄：</strong> <span id="modalAge">35岁</span></p>
                                    <p><strong>身份证号：</strong> <span id="modalIdCard">110***********1234</span></p>
                                </div>
                                <div class="col-md-6">
                                    <p><strong>联系电话：</strong> <span id="modalPhone">138****1234</span></p>
                                    <p><strong>紧急联系人：</strong> <span id="modalEmergency">李某某（配偶）</span></p>
                                    <p><strong>联系地址：</strong> <span id="modalAddress">北京市朝阳区xxx街道xxx号</span></p>
                                    <p><strong>医保类型：</strong> <span id="modalInsurance">城镇职工医保</span></p>
                                    <p><strong>过敏史：</strong> <span id="modalAllergy">青霉素过敏</span></p>
                                </div>
                            </div>
                        </div>
                        
                        <!-- 就诊记录 -->
                        <div role="tabpanel" class="tab-pane" id="medicalHistory">
                            <div class="timeline-item">
                                <div class="timeline-dot"></div>
                                <div class="timeline-content">
                                    <div class="timeline-date">2025-01-15 09:30</div>
                                    <p><strong>科室：</strong>内科 | <strong>医生：</strong>李医生</p>
                                    <p><strong>诊断：</strong>上呼吸道感染</p>
                                    <p><strong>处理：</strong>开具药物治疗，嘱咐多休息</p>
                                </div>
                            </div>
                            <div class="timeline-item">
                                <div class="timeline-dot"></div>
                                <div class="timeline-content">
                                    <div class="timeline-date">2024-12-20 14:20</div>
                                    <p><strong>科室：</strong>内科 | <strong>医生：</strong>王医生</p>
                                    <p><strong>诊断：</strong>急性胃炎</p>
                                    <p><strong>处理：</strong>静脉输液，开具胃药</p>
                                </div>
                            </div>
                        </div>
                        
                        <!-- 处方记录 -->
                        <div role="tabpanel" class="tab-pane" id="prescriptions">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>开具日期</th>
                                        <th>药品名称</th>
                                        <th>剂量</th>
                                        <th>用法</th>
                                        <th>天数</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>2025-01-15</td>
                                        <td>阿莫西林胶囊</td>
                                        <td>0.5g</td>
                                        <td>口服，每日3次</td>
                                        <td>5天</td>
                                    </tr>
                                    <tr>
                                        <td>2025-01-15</td>
                                        <td>布洛芬缓释胶囊</td>
                                        <td>0.3g</td>
                                        <td>口服，每日2次</td>
                                        <td>3天</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        
                        <!-- 检查结果 -->
                        <div role="tabpanel" class="tab-pane" id="examResults">
                            <div class="list-group">
                                <a href="#" class="list-group-item">
                                    <h5 class="list-group-item-heading">血常规检查</h5>
                                    <p class="list-group-item-text">
                                        检查日期：2025-01-15 | 
                                        结果：白细胞计数偏高 12.5×10^9/L
                                    </p>
                                </a>
                                <a href="#" class="list-group-item">
                                    <h5 class="list-group-item-heading">胸部X光</h5>
                                    <p class="list-group-item-text">
                                        检查日期：2025-01-15 | 
                                        结果：双肺纹理增粗，未见明显异常
                                    </p>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="editFromDetail()">
                        <i class="fa fa-edit"></i> 编辑信息
                    </button>
                    <button type="button" class="btn btn-success" onclick="createCase()">
                        <i class="fa fa-plus"></i> 创建病例
                    </button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>

    <!-- 新增/编辑患者模态框 -->
    <div class="modal fade" id="patientFormModal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="formModalTitle">新增患者</h4>
                </div>
                <form id="patientForm">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="required">姓名</label>
                                    <input type="text" class="form-control" name="name" required>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="required">性别</label>
                                    <select class="form-control" name="gender" required>
                                        <option value="">请选择</option>
                                        <option value="男">男</option>
                                        <option value="女">女</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="required">出生日期</label>
                                    <input type="date" class="form-control" name="birthDate" required>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="required">身份证号</label>
                                    <input type="text" class="form-control" name="idCard" required>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="required">联系电话</label>
                                    <input type="tel" class="form-control" name="phone" required>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>医保类型</label>
                                    <select class="form-control" name="insurance">
                                        <option value="">请选择</option>
                                        <option value="城镇职工医保">城镇职工医保</option>
                                        <option value="城镇居民医保">城镇居民医保</option>
                                        <option value="新农合">新农合</option>
                                        <option value="自费">自费</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>联系地址</label>
                            <input type="text" class="form-control" name="address">
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>紧急联系人</label>
                                    <input type="text" class="form-control" name="emergencyContact">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>紧急联系电话</label>
                                    <input type="tel" class="form-control" name="emergencyPhone">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>过敏史</label>
                            <textarea class="form-control" name="allergy" rows="2"></textarea>
                        </div>
                        <div class="form-group">
                            <label>备注</label>
                            <textarea class="form-control" name="remark" rows="2"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="submit" class="btn btn-primary">保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script>
        // 全选/取消全选
        $('#selectAll').on('change', function() {
            $('.patient-select').prop('checked', $(this).is(':checked'));
        });
        
        // 查看患者详情
        function viewPatient(patientId) {
            // 加载患者详细信息
            $('#modalPatientId').text(patientId);
            $('#patientDetailModal').modal('show');
        }
        
        // 编辑患者
        function editPatient(patientId) {
            $('#formModalTitle').text('编辑患者');
            // 加载患者信息到表单
            $('#patientFormModal').modal('show');
        }
        
        // 删除患者
        function deletePatient(patientId) {
            if (confirm('确定要删除患者 ' + patientId + ' 的信息吗？此操作不可恢复！')) {
                // 执行删除操作
                alert('患者信息已删除');
                // 刷新列表
                location.reload();
            }
        }
        
        // 新增患者
        function addNewPatient() {
            $('#formModalTitle').text('新增患者');
            $('#patientForm')[0].reset();
            $('#patientFormModal').modal('show');
        }
        
        // 从详情页编辑
        function editFromDetail() {
            $('#patientDetailModal').modal('hide');
            var patientId = $('#modalPatientId').text();
            editPatient(patientId);
        }
        
        // 创建病例
        function createCase() {
            var patientId = $('#modalPatientId').text();
            var patientName = $('#modalPatientName').text();
            // 跳转到病例填写页面，并传递患者信息
            window.location.href = 'case_record.jsp?patientId=' + patientId + '&patientName=' + patientName;
        }
        
        // 导出数据
        function exportPatients() {
            if (confirm('确定要导出患者数据吗？')) {
                alert('正在导出数据...');
            }
        }
        
        // 重置搜索
        function resetSearch() {
            $('#searchForm')[0].reset();
        }
        
        // 表单提交
        $('#patientForm').on('submit', function(e) {
            e.preventDefault();
            
            // 表单验证
            var phone = $('input[name="phone"]').val();
            if (!/^1[3-9]\d{9}$/.test(phone)) {
                alert('请输入正确的手机号');
                return false;
            }
            
            var idCard = $('input[name="idCard"]').val();
            if (!/^\d{17}[\dXx]$/.test(idCard)) {
                alert('请输入正确的身份证号');
                return false;
            }
            
            // 提交数据
            alert('患者信息保存成功！');
            $('#patientFormModal').modal('hide');
            // 刷新列表
            location.reload();
        });
        
        // 搜索表单提交
        $('#searchForm').on('submit', function(e) {
            e.preventDefault();
            // 执行搜索
            alert('正在搜索患者...')
        });
        
        // 初始化提示工具
        $(function () {
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>
</body>
</html>