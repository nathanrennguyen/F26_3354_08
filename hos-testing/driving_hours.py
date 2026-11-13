import re
from pathlib import Path


def convert_time(time_text):
    pattern = r"([0-9]{1,2})(?::([0-9]{1,2}))?\s*(am|pm)?"
    match = re.fullmatch(pattern, time_text.strip(), re.IGNORECASE)

    if not match:
        raise ValueError("Invalid time format")

    hour = int(match.group(1))
    minute = int(match.group(2) or 0)
    period = match.group(3)

    if minute > 59:
        raise ValueError("Invalid minutes")

    if period:
        if not 1 <= hour <= 12:
            raise ValueError("Invalid 12-hour time")

        if period.lower() == "am":
            hour %= 12
        else:
            hour = (hour % 12) + 12
    elif not 0 <= hour <= 23:
        raise ValueError("Invalid 24-hour time")

    return hour * 60 + minute


def calculate_hours(interval):
    parts = re.split(r"\s*[-–—]\s*", interval.strip())

    if len(parts) != 2:
        raise ValueError("Invalid interval")

    start = convert_time(parts[0])
    end = convert_time(parts[1])

    if end < start:
        end += 1440

    hours = (end - start) / 60
    return f"{hours:g} hour" if hours == 1 else f"{hours:g} hours"


def main():
    folder = Path(__file__).parent
    input_file = folder / "run.txt"
    output_file = folder / "output.txt"

    try:
        lines = input_file.read_text(encoding="utf-8-sig").splitlines()
    except (OSError, UnicodeError) as error:
        print(f"Error reading run.txt: {error}")
        return

    results = []

    for line in lines:
        if not line.strip():
            continue

        try:
            result = calculate_hours(line)
        except ValueError:
            result = "Error"

        results.append(result)

    try:
        output_file.write_text(
            "\n".join(results) + ("\n" if results else ""),
            encoding="utf-8"
        )
    except OSError as error:
        print(f"Error writing output.txt: {error}")
        return

    for result in results:
        print(result)


if __name__ == "__main__":
    main()
