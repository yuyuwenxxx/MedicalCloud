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
    <title>医疗云系统 - 病例填写</title>
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
        
        .case-form-container {
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            padding: 30px;
            margin-bottom: 30px;
        }
        
        .section-title {
            color: #0d5fc9;
            border-bottom: 2px solid #e3f2fd;
            padding-bottom: 10px;
            margin-bottom: 20px;
            font-size: 18px;
            font-weight: 600;
        }
        
        .form-group label {
            color: #495057;
            font-weight: 500;
            margin-bottom: 8px;
        }
        
        .form-control {
            border: 1px solid #ced4da;
            border-radius: 6px;
            padding: 10px 12px;
            transition: all 0.3s ease;
        }
        
        .form-control:focus {
            border-color: #0d5fc9;
            box-shadow: 0 0 0 3px rgba(13, 95, 201, 0.1);
        }
        
        .required:after {
            content: " *";
            color: #dc3545;
        }
        
        .btn-primary {
            background: linear-gradient(135deg, #0d5fc9, #0a4eaa);
            border: none;
            border-radius: 6px;
            padding: 12px 30px;
            font-weight: 600;
            transition: all 0.3s ease;
        }
        
        .btn-primary:hover {
            background: linear-gradient(135deg, #0a4eaa, #084191);
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(13, 95, 201, 0.3);
        }
        
        .btn-secondary {
            background-color: #6c757d;
            color: white;
            border: none;
            border-radius: 6px;
            padding: 12px 30px;
            font-weight: 600;
            margin-left: 10px;
        }
        
        .btn-success {
            background-color: #28a745;
            border: none;
            border-radius: 6px;
            padding: 12px 30px;
            font-weight: 600;
            margin-left: 10px;
        }
        
        .patient-info-card {
            background: linear-gradient(135deg, #f0f7ff, #e3f2fd);
            border-left: 4px solid #0d5fc9;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 25px;
        }
        
        .diagnosis-section {
            background: #fff9e6;
            border-left: 4px solid #ffc107;
            padding: 20px;
            border-radius: 8px;
            margin-top: 20px;
        }
        
        .prescription-section {
            background: #f0fff4;
            border-left: 4px solid #28a745;
            padding: 20px;
            border-radius: 8px;
            margin-top: 20px;
        }
        
        .medicine-item {
            background: white;
            border: 1px solid #dee2e6;
            border-radius: 6px;
            padding: 15px;
            margin-bottom: 15px;
            transition: all 0.3s ease;
        }
        
        .medicine-item:hover {
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        
        .add-medicine-btn {
            background-color: #17a2b8;
            color: white;
            border: none;
            padding: 8px 20px;
            border-radius: 4px;
            font-size: 14px;
            cursor: pointer;
        }
        
        .remove-medicine-btn {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            font-size: 12px;
            cursor: pointer;
            float: right;
        }
        
        .template-buttons {
            margin-bottom: 20px;
        }
        
        .template-btn {
            background-color: #f8f9fa;
            border: 1px solid #dee2e6;
            padding: 8px 15px;
            margin-right: 10px;
            margin-bottom: 10px;
            border-radius: 20px;
            cursor: pointer;
            transition: all 0.3s ease;
        }
        
        .template-btn:hover {
            background-color: #e3f2fd;
            border-color: #0d5fc9;
            color: #0d5fc9;
        }
        
        .alert-info {
            background-color: #d1ecf1;
            border: 1px solid #bee5eb;
            color: #0c5460;
            padding: 12px;
            border-radius: 4px;
            margin-bottom: 20px;
        }
        
        .status-badge {
            display: inline-block;
            padding: 4px 12px;
            border-radius: 20px;
            font-size: 12px;
            font-weight: 600;
        }
        
        .status-draft {
            background-color: #ffc107;
            color: #000;
        }
        
        .status-submitted {
            background-color: #17a2b8;
            color: white;
        }
        
        .status-completed {
            background-color: #28a745;
            color: white;
        }
    </style>
</head>
<body>
    <%
        // 检查用户是否登录且为医生
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        com.medical.model.User user = (com.medical.model.User) session.getAttribute("user");
        if (!"DOCTOR".equals(user.getUserType())) {
            response.sendRedirect("profile.jsp");
            return;
        }
    %>
    
    <!-- 系统头部 -->
    <div class="header">
        <div class="container">
            <h1>医疗云系统 - 医生工作站</h1>
            <p>病例填写与管理</p>
        </div>
    </div>

    <!-- 导航菜单 -->
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
                        data-target="#doctor-nav" aria-expanded="false">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="doctor_dashboard.jsp" class="navbar-brand">医生工作站</a>
            </div>
            <div class="collapse navbar-collapse" id="doctor-nav">
                <ul class="nav navbar-nav">
                    <li><a href="doctor_dashboard.jsp"><i class="fa fa-dashboard"></i> 工作台</a></li>
                    <li class="active"><a href="case_record.jsp"><i class="fa fa-edit"></i> 病例填写</a></li>
                    <li><a href="doctor_process.html"><i class="fa fa-stethoscope"></i> 就诊管理</a></li>
                    <li><a href="patient_manage.jsp"><i class="fa fa-users"></i> 患者管理</a></li>
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
            <h2><i class="fa fa-file-medical"></i> 病例填写</h2>
        </div>
    </div>

    <!-- 主要内容区域 -->
    <div class="container">
        <!-- 患者选择/信息卡片 -->
        <div class="patient-info-card">
            <div class="row">
                <div class="col-md-8">
                    <h4 style="color: #0d5fc9; margin-bottom: 15px;">患者信息</h4>
                    <div class="row">
                        <div class="col-md-6">
                            <p><strong>患者ID：</strong><span id="patientId">P2025001</span></p>
                            <p><strong>姓名：</strong><span id="patientName">张三</span></p>
                        </div>
                        <div class="col-md-6">
                            <p><strong>性别：</strong><span id="patientGender">男</span></p>
                            <p><strong>年龄：</strong><span id="patientAge">35岁</span></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 text-right">
                    <button class="btn btn-primary" onclick="selectPatient()">
                        <i class="fa fa-search"></i> 选择患者
                    </button>
                    <button class="btn btn-info" onclick="viewHistory()">
                        <i class="fa fa-history"></i> 查看病史
                    </button>
                </div>
            </div>
        </div>

        <!-- 病例填写表单 -->
        <div class="case-form-container">
            <form id="caseRecordForm" action="saveCaseRecord" method="post">
                <!-- 基本信息 -->
                <div class="section-title">基本信息</div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="required">就诊日期</label>
                            <input type="date" class="form-control" name="visitDate" 
                                   value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" required>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="required">就诊科室</label>
                            <select class="form-control" name="department" required>
                                <option value="">请选择科室</option>
                                <option value="内科">内科</option>
                                <option value="外科">外科</option>
                                <option value="妇科">妇科</option>
                                <option value="儿科">儿科</option>
                                <option value="眼科">眼科</option>
                                <option value="耳鼻喉科">耳鼻喉科</option>
                                <option value="皮肤科">皮肤科</option>
                                <option value="中医科">中医科</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label>病例状态</label>
                            <div style="margin-top: 10px;">
                                <span class="status-badge status-draft">草稿</span>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 主诉与现病史 -->
                <div class="section-title">主诉与现病史</div>
                <div class="template-buttons">
                    <button type="button" class="template-btn" onclick="useTemplate('感冒')">感冒模板</button>
                    <button type="button" class="template-btn" onclick="useTemplate('高血压')">高血压模板</button>
                    <button type="button" class="template-btn" onclick="useTemplate('糖尿病')">糖尿病模板</button>
                    <button type="button" class="template-btn" onclick="useTemplate('胃炎')">胃炎模板</button>
                </div>
                <div class="form-group">
                    <label class="required">主诉</label>
                    <textarea class="form-control" name="chiefComplaint" rows="3" 
                              placeholder="请描述患者的主要症状和持续时间..." required></textarea>
                </div>
                <div class="form-group">
                    <label class="required">现病史</label>
                    <textarea class="form-control" name="presentIllness" rows="5" 
                              placeholder="请详细描述疾病的发生、发展过程..." required></textarea>
                </div>

                <!-- 既往史 -->
                <div class="section-title">既往史</div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>既往疾病史</label>
                            <textarea class="form-control" name="pastHistory" rows="3" 
                                      placeholder="如：高血压、糖尿病、手术史等..."></textarea>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>过敏史</label>
                            <textarea class="form-control" name="allergyHistory" rows="3" 
                                      placeholder="药物过敏、食物过敏等..."></textarea>
                        </div>
                    </div>
                </div>

                <!-- 体格检查 -->
                <div class="section-title">体格检查</div>
                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>体温 (°C)</label>
                            <input type="number" step="0.1" class="form-control" name="temperature" 
                                   placeholder="36.5">
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>血压 (mmHg)</label>
                            <input type="text" class="form-control" name="bloodPressure" 
                                   placeholder="120/80">
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>心率 (次/分)</label>
                            <input type="number" class="form-control" name="heartRate" 
                                   placeholder="72">
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>呼吸 (次/分)</label>
                            <input type="number" class="form-control" name="respiratory" 
                                   placeholder="16">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label>体格检查描述</label>
                    <textarea class="form-control" name="physicalExam" rows="4" 
                              placeholder="请描述体格检查结果..."></textarea>
                </div>

                <!-- 辅助检查 -->
                <div class="section-title">辅助检查</div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>检查项目</label>
                            <div class="checkbox">
                                <label><input type="checkbox" name="examItems" value="血常规"> 血常规</label>
                            </div>
                            <div class="checkbox">
                                <label><input type="checkbox" name="examItems" value="尿常规"> 尿常规</label>
                            </div>
                            <div class="checkbox">
                                <label><input type="checkbox" name="examItems" value="肝功能"> 肝功能</label>
                            </div>
                            <div class="checkbox">
                                <label><input type="checkbox" name="examItems" value="肾功能"> 肾功能</label>
                            </div>
                            <div class="checkbox">
                                <label><input type="checkbox" name="examItems" value="心电图"> 心电图</label>
                            </div>
                            <div class="checkbox">
                                <label><input type="checkbox" name="examItems" value="胸部X光"> 胸部X光</label>
                            </div>
                            <div class="checkbox">
                                <label><input type="checkbox" name="examItems" value="CT"> CT</label>
                            </div>
                            <div class="checkbox">
                                <label><input type="checkbox" name="examItems" value="B超"> B超</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>检查结果</label>
                            <textarea class="form-control" name="examResults" rows="8" 
                                      placeholder="请输入检查结果..."></textarea>
                        </div>
                    </div>
                </div>

                <!-- 诊断 -->
                <div class="diagnosis-section">
                    <div class="section-title" style="border-color: #ffc107;">诊断</div>
                    <div class="form-group">
                        <label class="required">初步诊断</label>
                        <input type="text" class="form-control" name="diagnosis" 
                               placeholder="请输入诊断结果..." required>
                    </div>
                    <div class="form-group">
                        <label>诊断依据</label>
                        <textarea class="form-control" name="diagnosisBasis" rows="3" 
                                  placeholder="请说明诊断依据..."></textarea>
                    </div>
                </div>

                <!-- 治疗方案 -->
                <div class="prescription-section">
                    <div class="section-title" style="border-color: #28a745;">治疗方案</div>
                    <div class="form-group">
                        <label>治疗原则</label>
                        <textarea class="form-control" name="treatmentPrinciple" rows="3" 
                                  placeholder="请输入治疗原则..."></textarea>
                    </div>
                    
                    <!-- 处方药品 -->
                    <div class="form-group">
                        <label>处方药品</label>
                        <button type="button" class="add-medicine-btn" onclick="addMedicine()">
                            <i class="fa fa-plus"></i> 添加药品
                        </button>
                        <div id="medicineList" style="margin-top: 15px;">
                            <div class="medicine-item">
                                <button type="button" class="remove-medicine-btn" onclick="removeMedicine(this)">
                                    <i class="fa fa-times"></i> 删除
                                </button>
                                <div class="row">
                                    <div class="col-md-4">
                                        <input type="text" class="form-control" name="medicineName[]" 
                                               placeholder="药品名称">
                                    </div>
                                    <div class="col-md-2">
                                        <input type="text" class="form-control" name="medicineDosage[]" 
                                               placeholder="剂量">
                                    </div>
                                    <div class="col-md-2">
                                        <input type="text" class="form-control" name="medicineFrequency[]" 
                                               placeholder="用法">
                                    </div>
                                    <div class="col-md-2">
                                        <input type="text" class="form-control" name="medicineDays[]" 
                                               placeholder="天数">
                                    </div>
                                    <div class="col-md-2">
                                        <input type="text" class="form-control" name="medicineQuantity[]" 
                                               placeholder="数量">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- 医嘱 -->
                    <div class="form-group">
                        <label>医嘱</label>
                        <textarea class="form-control" name="medicalAdvice" rows="3" 
                                  placeholder="饮食、休息、复诊等注意事项..."></textarea>
                    </div>
                </div>

                <!-- 操作按钮 -->
                <div class="text-center" style="margin-top: 30px;">
                    <button type="button" class="btn btn-secondary" onclick="saveDraft()">
                        <i class="fa fa-save"></i> 保存草稿
                    </button>
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-check"></i> 提交病例
                    </button>
                    <button type="button" class="btn btn-success" onclick="submitAndPrint()">
                        <i class="fa fa-print"></i> 提交并打印
                    </button>
                </div>
            </form>
        </div>
    </div>

    <!-- 患者选择模态框 -->
    <div class="modal fade" id="patientSelectModal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">选择患者</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <input type="text" class="form-control" id="searchPatient" 
                               placeholder="搜索患者姓名或ID...">
                    </div>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>患者ID</th>
                                <th>姓名</th>
                                <th>性别</th>
                                <th>年龄</th>
                                <th>联系电话</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody id="patientList">
                            <tr>
                                <td>P2025001</td>
                                <td>张三</td>
                                <td>男</td>
                                <td>35</td>
                                <td>138****1234</td>
                                <td><button class="btn btn-sm btn-primary" onclick="choosePatient('P2025001', '张三', '男', '35')">选择</button></td>
                            </tr>
                            <tr>
                                <td>P2025002</td>
                                <td>李四</td>
                                <td>女</td>
                                <td>28</td>
                                <td>139****5678</td>
                                <td><button class="btn btn-sm btn-primary" onclick="choosePatient('P2025002', '李四', '女', '28')">选择</button></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script>
        // 选择患者
        function selectPatient() {
            $('#patientSelectModal').modal('show');
        }
        
        // 选中患者
        function choosePatient(id, name, gender, age) {
            $('#patientId').text(id);
            $('#patientName').text(name);
            $('#patientGender').text(gender);
            $('#patientAge').text(age + '岁');
            $('#patientSelectModal').modal('hide');
        }
        
        // 查看病史
        function viewHistory() {
            alert('正在加载患者病史...');
        }
        
        // 使用模板
        function useTemplate(type) {
            var templates = {
                '感冒': {
                    chiefComplaint: '发热、咳嗽3天',
                    presentIllness: '患者3天前无明显诱因出现发热，体温最高达38.5°C，伴咳嗽、咳白色粘痰，无胸痛、气促。自服感冒药后症状无明显缓解。',
                    diagnosis: '上呼吸道感染'
                },
                '高血压': {
                    chiefComplaint: '头晕、头痛1周',
                    presentIllness: '患者1周前出现头晕、头痛，以额部为主，呈持续性钝痛，休息后可缓解。测血压160/100mmHg。',
                    diagnosis: '原发性高血压'
                },
                '糖尿病': {
                    chiefComplaint: '口干、多饮、多尿2周',
                    presentIllness: '患者2周前出现口干、多饮、多尿症状，每日饮水量约3000ml，夜尿3-4次，伴乏力、体重下降。',
                    diagnosis: '2型糖尿病'
                },
                '胃炎': {
                    chiefComplaint: '上腹痛、反酸1个月',
                    presentIllness: '患者1个月前出现上腹部隐痛，餐后加重，伴反酸、嗳气，无黑便、呕血。',
                    diagnosis: '慢性胃炎'
                }
            };
            
            if (templates[type]) {
                $('textarea[name="chiefComplaint"]').val(templates[type].chiefComplaint);
                $('textarea[name="presentIllness"]').val(templates[type].presentIllness);
                $('input[name="diagnosis"]').val(templates[type].diagnosis);
            }
        }
        
        // 添加药品
        function addMedicine() {
            var medicineHtml = `
                <div class="medicine-item">
                    <button type="button" class="remove-medicine-btn" onclick="removeMedicine(this)">
                        <i class="fa fa-times"></i> 删除
                    </button>
                    <div class="row">
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="medicineName[]" 
                                   placeholder="药品名称">
                        </div>
                        <div class="col-md-2">
                            <input type="text" class="form-control" name="medicineDosage[]" 
                                   placeholder="剂量">
                        </div>
                        <div class="col-md-2">
                            <input type="text" class="form-control" name="medicineFrequency[]" 
                                   placeholder="用法">
                        </div>
                        <div class="col-md-2">
                            <input type="text" class="form-control" name="medicineDays[]" 
                                   placeholder="天数">
                        </div>
                        <div class="col-md-2">
                            <input type="text" class="form-control" name="medicineQuantity[]" 
                                   placeholder="数量">
                        </div>
                    </div>
                </div>
            `;
            $('#medicineList').append(medicineHtml);
        }
        
        // 删除药品
        function removeMedicine(btn) {
            if ($('.medicine-item').length > 1) {
                $(btn).closest('.medicine-item').remove();
            } else {
                alert('至少需要保留一个药品输入框');
            }
        }
        
        // 保存草稿
        function saveDraft() {
            alert('病例已保存为草稿');
            $('.status-badge').removeClass('status-submitted').addClass('status-draft').text('草稿');
        }
        
        // 提交并打印
        function submitAndPrint() {
            if (confirm('确定要提交病例并打印吗？')) {
                $('#caseRecordForm').submit();
                setTimeout(function() {
                    window.print();
                }, 1000);
            }
        }
        
        // 表单验证
        $('#caseRecordForm').on('submit', function(e) {
            e.preventDefault();
            
            // 检查是否选择了患者
            var patientId = $('#patientId').text();
            if (!patientId || patientId === '') {
                alert('请先选择患者');
                return false;
            }
            
            // 检查必填字段
            var required = ['visitDate', 'department', 'chiefComplaint', 'presentIllness', 'diagnosis'];
            for (var i = 0; i < required.length; i++) {
                var field = $('[name="' + required[i] + '"]');
                if (!field.val()) {
                    alert('请填写完整的必填信息');
                    field.focus();
                    return false;
                }
            }
            
            if (confirm('确定要提交病例吗？')) {
                alert('病例提交成功！');
                $('.status-badge').removeClass('status-draft').addClass('status-submitted').text('已提交');
                // 实际提交表单
                // this.submit();
            }
        });
        
        // 搜索患者
        $('#searchPatient').on('keyup', function() {
            var searchText = $(this).val().toLowerCase();
            $('#patientList tr').filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(searchText) > -1);
            });
        });
    </script>
</body>
</html>