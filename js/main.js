$(document).ready(function() {
    // 获取功能模块元素
    var registration = $("#registration");
    var history = $("#history");
    var profile = $("#profile");
    var featureCards = $("#registration-card, #history-card, #profile-card");
    var navItems = $("#nav-registration, #nav-history, #nav-profile");
    var navLinks = $("ul.nav.navbar-nav li");
    
    // 隐藏所有功能模块内容
    function hideAllModules() {
        registration.hide();
        history.hide();
        profile.hide();
        // 隐藏功能卡片
        featureCards.closest('.row').hide();
        // 隐藏欢迎区域
        $('.welcome-section').hide();
    }
    
    // 重置导航栏活动状态
    function resetNavActive() {
        navLinks.removeClass('active');
    }
    
    // 显示首页
    function showHomePage() {
        // 显示功能卡片和欢迎区域
        featureCards.closest('.row').show();
        $('.welcome-section').show();
        // 隐藏所有功能模块内容
        registration.hide();
        history.hide();
        profile.hide();
        // 设置首页导航为活动状态
        resetNavActive();
        navLinks.first().addClass('active');
    }
    
    // 导航栏点击事件 - 首页
    navLinks.first().on('click', function(e) {
        e.preventDefault();
        showHomePage();
    });
    
    // 导航栏点击事件 - 预约挂号
    $("#nav-registration").on('click', function(e) {
        e.preventDefault();
        hideAllModules();
        registration.show();
        resetNavActive();
        $(this).parent().addClass('active');
    });
    
    // 导航栏点击事件 - 看病历史
    $("#nav-history").on('click', function(e) {
        e.preventDefault();
        hideAllModules();
        history.show();
        resetNavActive();
        $(this).parent().addClass('active');
    });
    
    // 导航栏点击事件 - 我的信息
    $("#nav-profile").on('click', function(e) {
        e.preventDefault();
        hideAllModules();
        profile.show();
        resetNavActive();
        $(this).parent().addClass('active');
    });
    
    // 功能卡片点击事件 - 预约挂号
    $("#registration-card").on('click', function() {
        hideAllModules();
        registration.show();
        resetNavActive();
        $("#nav-registration").parent().addClass('active');
    });
    
    // 功能卡片点击事件 - 看病历史
    $("#history-card").on('click', function() {
        hideAllModules();
        history.show();
        resetNavActive();
        $("#nav-history").parent().addClass('active');
    });
    
    // 功能卡片点击事件 - 我的信息
    $("#profile-card").on('click', function() {
        hideAllModules();
        profile.show();
        resetNavActive();
        $("#nav-profile").parent().addClass('active');
    });
    
    // 功能卡片内的按钮阻止冒泡
    $('.feature-card button').on('click', function(e) {
        e.stopPropagation();
    });

    // 搜索功能
    $('.btn-search').on('click', function() {
        var keyword = $(this).closest('.input-group').find('input').val();
        if (keyword) {
            alert('搜索: ' + keyword);
        }
    });

    // 表单提交
    $('#registration form').on('submit', function(e) {
        e.preventDefault();
        var name = $('#patientname').val();
        var time = $('#appointmentData').val();
        var department = $('#departFormControlSelect1').val();
        
        if (name && time && department) {
            alert('预约成功！\n姓名: ' + name + '\n时间: ' + time + '\n科室: ' + department);
        } else {
            alert('请填写完整信息！');
        }
    });
});