package com.zodiac.polit.bean.response;

public class UpdateResponse {


    /**
     * code : 0
     * message : 成功
     * content : {"id":"1","isNewRecord":false,"createDate":"2019-09-08 01:36:24","updateBy":{"id
     * ":"1","isNewRecord":false,"pageNo":0,"pageSize":0,"loginFlag":"1","admin":true,
     * "roleNames":""},"updateDate":"2019-09-08 01:36:24","pageNo":0,"pageSize":0,
     * "appUrl":"/airforce/userfiles/1/files/app/2019/09/file201909080135503765546.apk",
     * "appVersion":"1.0.1","appRemarks":"更新版本","appForced":"1"}
     */

    private String code;
    private String message;
    private ContentBean content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * id : 1
         * isNewRecord : false
         * createDate : 2019-09-08 01:36:24
         * updateBy : {"id":"1","isNewRecord":false,"pageNo":0,"pageSize":0,"loginFlag":"1",
         * "admin":true,"roleNames":""}
         * updateDate : 2019-09-08 01:36:24
         * pageNo : 0
         * pageSize : 0
         * appUrl : /airforce/userfiles/1/files/app/2019/09/file201909080135503765546.apk
         * appVersion : 1.0.1
         * appRemarks : 更新版本
         * appForced : 1
         */

        private String id;
        private boolean isNewRecord;
        private String createDate;
        private UpdateByBean updateBy;
        private String updateDate;
        private int pageNo;
        private int pageSize;
        private String appUrl;
        private String appVersion;
        private String appRemarks;
        private String appForced;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public UpdateByBean getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(UpdateByBean updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public String getAppUrl() {
            return appUrl;
        }

        public void setAppUrl(String appUrl) {
            this.appUrl = appUrl;
        }

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        public String getAppRemarks() {
            return appRemarks;
        }

        public void setAppRemarks(String appRemarks) {
            this.appRemarks = appRemarks;
        }

        public String getAppForced() {
            return appForced;
        }

        public void setAppForced(String appForced) {
            this.appForced = appForced;
        }

        public static class UpdateByBean {
            /**
             * id : 1
             * isNewRecord : false
             * pageNo : 0
             * pageSize : 0
             * loginFlag : 1
             * admin : true
             * roleNames :
             */

            private String id;
            private boolean isNewRecord;
            private int pageNo;
            private int pageSize;
            private String loginFlag;
            private boolean admin;
            private String roleNames;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isIsNewRecord() {
                return isNewRecord;
            }

            public void setIsNewRecord(boolean isNewRecord) {
                this.isNewRecord = isNewRecord;
            }

            public int getPageNo() {
                return pageNo;
            }

            public void setPageNo(int pageNo) {
                this.pageNo = pageNo;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public String getLoginFlag() {
                return loginFlag;
            }

            public void setLoginFlag(String loginFlag) {
                this.loginFlag = loginFlag;
            }

            public boolean isAdmin() {
                return admin;
            }

            public void setAdmin(boolean admin) {
                this.admin = admin;
            }

            public String getRoleNames() {
                return roleNames;
            }

            public void setRoleNames(String roleNames) {
                this.roleNames = roleNames;
            }
        }
    }
}
