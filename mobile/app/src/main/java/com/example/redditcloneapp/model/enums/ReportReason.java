package com.example.redditcloneapp.model.enums;

import androidx.annotation.NonNull;

public enum ReportReason {
    BREAKS_RULES("Breaks rules"),
    HARASSMENT("Harassment"),
    HATE("Hate"),
    SHARING_PERSONAL_INFORMATION("Sharing personal information"),
    IMPERSONATION("Impersonation"),
    COPYRIGHT_VIOLATION("Copyright violation"),
    TRADEMARK_VIOLATION("Trademark violation"),
    SPAM("Spam"),
    SELF_HARM_OR_SUICIDE ("Self harm or suicide"),
    OTHER ("Other..");


    private String ruleName;

    private ReportReason(String s) {
        this.ruleName = s;
    }

    @NonNull
    @Override
    public String toString() {
        return ruleName;
    }
}
