export enum ReportReason {
    BREAKS_RULES = 0,
    HARASSMENT = 1,
    HATE = 2,
    SHARING_PERSONAL_INFORMATION = 3,
    IMPERSONATION = 4,
    COPYRIGHT_VIOLATION = 5,
    TRADEMARK_VIOLATION = 6,
    SPAM = 7,
    SELF_HARM_OR_SUICIDE = 8,
    OTHER = 9
    
}

export const ReasonMapping: Record<ReportReason, string> = {
    [ReportReason.BREAKS_RULES]: "Breaks rules",
    [ReportReason.HARASSMENT]: "Harassment",
    [ReportReason.HATE]: "Hate",
    [ReportReason.SHARING_PERSONAL_INFORMATION]: "Sharing personal info",
    [ReportReason.IMPERSONATION]: "Impersonation",
    [ReportReason.COPYRIGHT_VIOLATION]: "Copyright violation",
    [ReportReason.TRADEMARK_VIOLATION]: "Trademark violation",
    [ReportReason.SPAM]: "Spam",
    [ReportReason.SELF_HARM_OR_SUICIDE]: "Self harm or suicide",
    [ReportReason.OTHER]: "Other..",
};
