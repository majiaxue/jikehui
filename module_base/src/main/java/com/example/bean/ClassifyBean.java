package com.example.bean;

import java.io.Serializable;
import java.util.List;

public class ClassifyBean implements Serializable {
    private List<Records> records;

    public List<Records> getRecords() {
        return records;
    }

    public void setRecords(List<Records> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "ClassifyBean{" +
                "records=" + records +
                '}';
    }

    public static class Records {
        private Integer id;
        private Integer parentId;
        private String name;
        private String level;
        private String productCount;
        private String productUnit;
        private String navStatus;
        private String showStatus;
        private String sort;
        private String icon;
        private String keywords;
        private String description;
        private String path;
        private String productAttributeIdList;
        private boolean isSelect;
        private List<RecordsSecond> children;

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public Integer getId() {
            return id;
        }

        public Integer getParentId() {
            return parentId;
        }

        public String getName() {
            return name;
        }

        public String getLevel() {
            return level;
        }

        public String getProductCount() {
            return productCount;
        }

        public String getProductUnit() {
            return productUnit;
        }

        public String getNavStatus() {
            return navStatus;
        }

        public String getShowStatus() {
            return showStatus;
        }

        public String getSort() {
            return sort;
        }

        public String getIcon() {
            return icon;
        }

        public String getKeywords() {
            return keywords;
        }

        public String getDescription() {
            return description;
        }

        public String getPath() {
            return path;
        }

        public String getProductAttributeIdList() {
            return productAttributeIdList;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public List<RecordsSecond> getChildren() {
            return children;
        }

        @Override
        public String toString() {
            return "ClassifyBean{" +
                    "id='" + id + '\'' +
                    ", parentId='" + parentId + '\'' +
                    ", name='" + name + '\'' +
                    ", level='" + level + '\'' +
                    ", productCount='" + productCount + '\'' +
                    ", productUnit='" + productUnit + '\'' +
                    ", navStatus='" + navStatus + '\'' +
                    ", showStatus='" + showStatus + '\'' +
                    ", sort='" + sort + '\'' +
                    ", icon='" + icon + '\'' +
                    ", keywords='" + keywords + '\'' +
                    ", description='" + description + '\'' +
                    ", path='" + path + '\'' +
                    ", productAttributeIdList='" + productAttributeIdList + '\'' +
                    ", isSelect=" + isSelect +
                    ", children=" + children +
                    '}';
        }

        public static class RecordsSecond {
            private Integer id;
            private Integer parentId;
            private String name;
            private String level;
            private String productCount;
            private String productUnit;
            private String navStatus;
            private String showStatus;
            private String sort;
            private String icon;
            private String keywords;
            private String description;
            private String path;
            private String productAttributeIdList;
            private boolean isSelect;
            private List<RecordsThird> children;

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public Integer getId() {
                return id;
            }

            public Integer getParentId() {
                return parentId;
            }

            public String getName() {
                return name;
            }

            public String getLevel() {
                return level;
            }

            public String getProductCount() {
                return productCount;
            }

            public String getProductUnit() {
                return productUnit;
            }

            public String getNavStatus() {
                return navStatus;
            }

            public String getShowStatus() {
                return showStatus;
            }

            public String getSort() {
                return sort;
            }

            public String getIcon() {
                return icon;
            }

            public String getKeywords() {
                return keywords;
            }

            public String getDescription() {
                return description;
            }

            public String getPath() {
                return path;
            }

            public String getProductAttributeIdList() {
                return productAttributeIdList;
            }

            public boolean isSelect() {
                return isSelect;
            }

            public List<RecordsThird> getChildren() {
                return children;
            }

            @Override
            public String toString() {
                return "RecordsSecond{" +
                        "id=" + id +
                        ", parentId=" + parentId +
                        ", name='" + name + '\'' +
                        ", level='" + level + '\'' +
                        ", productCount='" + productCount + '\'' +
                        ", productUnit='" + productUnit + '\'' +
                        ", navStatus='" + navStatus + '\'' +
                        ", showStatus='" + showStatus + '\'' +
                        ", sort='" + sort + '\'' +
                        ", icon='" + icon + '\'' +
                        ", keywords='" + keywords + '\'' +
                        ", description='" + description + '\'' +
                        ", path='" + path + '\'' +
                        ", productAttributeIdList='" + productAttributeIdList + '\'' +
                        ", isSelect=" + isSelect +
                        ", children='" + children + '\'' +
                        '}';
            }

            public static class RecordsThird {
                private Integer id;
                private Integer parentId;
                private String name;
                private String level;
                private String productCount;
                private String productUnit;
                private String navStatus;
                private String showStatus;
                private String sort;
                private String icon;
                private String keywords;
                private String description;
                private String path;
                private String productAttributeIdList;
                private boolean isSelect;
                private String children;

                public void setSelect(boolean select) {
                    isSelect = select;
                }

                public Integer getId() {
                    return id;
                }

                public Integer getParentId() {
                    return parentId;
                }

                public String getName() {
                    return name;
                }

                public String getLevel() {
                    return level;
                }

                public String getProductCount() {
                    return productCount;
                }

                public String getProductUnit() {
                    return productUnit;
                }

                public String getNavStatus() {
                    return navStatus;
                }

                public String getShowStatus() {
                    return showStatus;
                }

                public String getSort() {
                    return sort;
                }

                public String getIcon() {
                    return icon;
                }

                public String getKeywords() {
                    return keywords;
                }

                public String getDescription() {
                    return description;
                }

                public String getPath() {
                    return path;
                }

                public String getProductAttributeIdList() {
                    return productAttributeIdList;
                }

                public boolean isSelect() {
                    return isSelect;
                }

                public String getChildren() {
                    return children;
                }

                @Override
                public String toString() {
                    return "RecordThird{" +
                            "id=" + id +
                            ", parentId=" + parentId +
                            ", name='" + name + '\'' +
                            ", level='" + level + '\'' +
                            ", productCount='" + productCount + '\'' +
                            ", productUnit='" + productUnit + '\'' +
                            ", navStatus='" + navStatus + '\'' +
                            ", showStatus='" + showStatus + '\'' +
                            ", sort='" + sort + '\'' +
                            ", icon='" + icon + '\'' +
                            ", keywords='" + keywords + '\'' +
                            ", description='" + description + '\'' +
                            ", path='" + path + '\'' +
                            ", productAttributeIdList='" + productAttributeIdList + '\'' +
                            ", isSelect=" + isSelect +
                            ", children='" + children + '\'' +
                            '}';
                }
            }
        }
    }
}
